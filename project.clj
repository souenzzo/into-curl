(defproject into-curl "0.1.0-SNAPSHOT"
  :description "Print clj-http as a curl."
  :url "https://github.com/souenzzo/into-curl"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main into-curl.core
  :source-paths ["src"]
  :profiles {:dev {:source-paths ["src" "test"]
                   :dependencies [[midje/midje "1.9.2"]
                                  [clj-http/clj-http "3.9.0"]
                                  [org.clojure/test.check "0.10.0-alpha3"]]}})
