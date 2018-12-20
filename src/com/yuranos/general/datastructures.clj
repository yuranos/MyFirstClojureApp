(ns com.yuranos.general.datastructures)
;Two variants of how to get key from map2 where values don't match
(def map1 {1 44 2 33})
(def map2 {1 55 2 33})

(defn not=quantities [[id qty]] (not= (map1 id) qty))
(keys (filter not=quantities map2))

(def map3 {1 44 2 33})
(def map4 {1 55 2 33 3 15})

;maps are functions
;(merge-with not= map3 map4) will return a collection but it will be used as a function (pred coll_element)
(filter (merge-with not= map3 map4) (keys map4))


;Maps and applies
(def A [[1 2][3 4]])

  (apply map vector A) ;  ([1 3] [2 4])

(map (partial apply +) [[1 2] [3 4]]) ; => (3 7)


(defstruct mystruct :foo :bar)

(struct mystruct "eggplant" "pizza")
;{:foo "eggplant", :bar "pizza"}
(struct mystruct "eggplant")
;{:foo "eggplant", :bar nil}
(struct mystruct)
;{:foo nil, :bar nil}


