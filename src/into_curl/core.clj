(ns into-curl.core
  (:require [clojure.string :as string]))

(defmulti argument (fn [k v] k))
(defmethod argument :default
  [_ _]
  [])

(defmethod argument :method
  [k v]
  (if (= v "GET")
    []
    {:type   :short
     :option "X"
     :value  v}))

(defmethod argument :url
  [k v]
  {:type  :unique
   :value v})

(defmethod argument :accept
  [k v]
  (argument :headers {"Accept" v}))


(defmethod argument :content-type
  [k v]
  (argument :headers {"Content-Type" v}))

(defmethod argument :body
  [k v]
  {:type   :short
   :option "d"
   :value  v})

(defmethod argument :headers
  [k headers]
  (for [[k v] headers]
    {:option "H"
     :value  (format "%s: %s" k v)}))

(defn argument->cli
  [{:keys [option value type]}]
  (case type
    :short (format "'-%s%s'" option value)
    :unique (format "'%s'" value)
    (format "-%s '%s'" option value)))

(defn ->curl
  [{:as args}]
  (->> (for [[k v] args
             :let [result (argument k v)
                   result (if (map? result) [result] result)]]
         result)
       (into ["curl"] (comp cat (map argument->cli) (filter string?)))
       (string/join " ")))