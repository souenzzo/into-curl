# into-curl
Print clj-http as a curl.

## Usage
Latest stable release: `0.1.0`

`lein`/`boot`:
```clojure
[into-curl "0.1.0"]
```
`deps.end`:
```clojure
into-curl {:mvn/version "0.1.0"}
```
Example:
```clojure
(require [into-curl.core :refer [->curl]])

(->curl {:method "GET" :url "https://github.com/souenzzo/into-curl"})
;; => curl 'https://github.com/souenzzo/into-curl'
```
