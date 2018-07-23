# into-curl
Print clj-http as a curl.

## Usage

```clojure
(require [into-curl.core :refer [->curl]])

(->curl {:method "GET" :url "https://github.com/souenzzo/into-curl"}) ;; => curl 'https://github.com/souenzzo/into-curl' 
```
