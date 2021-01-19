(ns caesarhu.unqualify
    (:require [clojure.walk :refer [postwalk]]))


(defn transform-keys
  "Recursively transforms all map keys in coll with t."
  [t coll]
  (letfn [(transform [[k v]] [(t k) v])]
    (postwalk (fn [x] (if (map? x) (into {} (map transform x)) x)) coll)))

(defprotocol Unqualifiable
  (unqualify
    [obj recursive?]
    [obj]))

(extend-protocol Unqualifiable
  clojure.lang.Keyword
  (unqualify [obj]
    (if (qualified-keyword? obj)
      (-> obj name keyword)
      obj))

  clojure.lang.Symbol
  (unqualify [obj]
    (if (qualified-symbol? obj)
      (-> obj name symbol)
      obj))

  clojure.lang.PersistentHashMap
  (unqualify
    ([obj recursive?]
     (if recursive?
       (transform-keys unqualify obj)
       (let [all-keys (->> (keys obj)
                           (map unqualify))
             all-vals (vals obj)]
         (zipmap all-keys all-vals))))
    ([obj]
     (unqualify obj false)))

  clojure.lang.PersistentArrayMap
  (unqualify
    ([obj recursive?]
     (if recursive?
       (transform-keys unqualify obj)
       (let [all-keys (->> (keys obj)
                           (map unqualify))
             all-vals (vals obj)]
         (zipmap all-keys all-vals))))
    ([obj]
     (unqualify obj false))))

