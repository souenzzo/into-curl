(ns into-curl.core-test
  (:require [into-curl.core :refer [->curl]]
            [clojure.test :refer [deftest]]
            [midje.sweet :refer :all]))

(deftest core
  (fact
    "simple get"
    (->curl {:method "GET"
             :url    "https://github.com/souenzzo/into-curl"})
    => "curl 'http://foo.com'")
  (fact
    "get with accept"
    (->curl {:method "GET"
             :accept "application/edn"
             :url    "http://foo.com"})
    => "curl -H 'Accept: application/edn' 'http://foo.com'")
  (fact
    "simple post"
    (->curl {:method "POST"
             :url    "http://foo.com"
             :body   "{\"hello\": 42}"})
    => "curl '-XPOST' 'http://foo.com' '-d{\"hello\": 42}'")
  (fact
    "headers"
    (->curl {:method       "GET"
             :headers      {"foo" "bar" "var" "car"}
             :content-type "application/json"
             :url          "http://foo.com"})
    => "curl -H 'foo: bar' -H 'var: car' -H 'Content-Type: application/json' 'http://foo.com'"))
