(ns into-curl.core-test
  (:require [into-curl.core :refer [->curl]]
            [clojure.test :refer [deftest]]
            [midje.sweet :refer :all]))

(deftest core
  (fact
    "simple get"
    (->curl {:method "GET"
             :url    "http://foo.com"})
    => "curl \"http://foo.com\"")
  (fact
    "get with accept"
    (->curl {:method "GET"
             :accept "application/edn"
             :url    "http://foo.com"})
    => "curl -H \"Accept: application/edn\" \"http://foo.com\"")
  (fact
    "simple post"
    (->curl {:method "POST"
             :url    "http://foo.com"
             :body   "{\"hello\": 42}"})
    => "curl \"-XPOST\" \"http://foo.com\" \"-d{\\\"hello\\\": 42}\"")
  (fact
    "escape"
    (fact
      "simple post"
      (->curl {:method "POST"
               :url    "http://foo.com/$VAR/\"/'/\\"
               :body   "{\"hello${INJECT}\": 42}"})
      => "curl \"-XPOST\" \"http://foo.com/\\$VAR/\\\"/'/\\\\\" \"-d{\\\"hello\\${INJECT}\\\": 42}\""))
  (fact
    "headers"
    (->curl {:method       "GET"
             :headers      {"foo" "bar" "var" "car"}
             :content-type "application/json"
             :url          "http://foo.com"})
    => "curl -H \"foo: bar\" -H \"var: car\" -H \"Content-Type: application/json\" \"http://foo.com\""))

(deftest ring
  (fact
    (->curl :ring/request {:protocol "HTTP/1.1",
                           :server-port 1634,
                           :uri "/oMYTP6+h7pwSJ",
                           :server-name "2XNC9TGlM33i7B",
                           :query-string "XtUQqCV=F3e5Hw,J6YaojpC7E6okf&i70PYg=Kc2C4,&ViR50QW=Tprj-35vr-WRJ/q/eRIB68E.E&x8M2X5t6K710rt2wvV7In5PYvCs68H=KC0K5NwDahjFp_Jqu08ba.MaR9KY&4HJO=oOD/3DA,ik8_m&3kazLZr07AJ95Nj4TVU6Ny1p9=b~sipxk5El,Hk/ujdd&F9aN1tXmS0F1mA2YWG07U8q=&M0uD5XM=l0VSex0TX/gwf3Wjlka&8Yhh8ql5h=BItA3yl0u&391=hp&mwDGxIWm08ixBly9c=JyrJiXC4JK7eN__g3qNz+Q+InKmS&989GV89Qk0n8Sv518pKJQz1WH66=xisbL4-xPnm1OVM",
                           :scheme :https,
                           :request-method :POST})
    => "curl https://2XNC9TGlM33i7B:1634/oMYTP6+h7pwSJ -XPOST"))
