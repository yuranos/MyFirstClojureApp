(ns com.yuranos.general.datastructures
  (:require [clojure.set :as set]))
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

;; Note the equivalence of the following two forms
;So, apply is good when youa lready have a vector
(apply str ["str1" "str2" "str3"])  ;;=> "str1str2str3"
(str "str1" "str2" "str3")          ;;=> "str1str2str3"


(defstruct mystruct :foo :bar)

(struct mystruct "eggplant" "pizza")
;{:foo "eggplant", :bar "pizza"}
(struct mystruct "eggplant")
;{:foo "eggplant", :bar nil}
(struct mystruct)
;{:foo nil, :bar nil}

;MapStruct
;Random cahr seq generator
(defn fixed-length-name-generator
  ([] (fixed-length-name-generator 8))
  ([n]
   (let [chars (map char (concat (range 65 90) (range 97 122)))
         password (take n (repeatedly #(rand-nth chars)))]
     (reduce str password))))

;Map struct
(defstruct attendee :firstname :lastname)

(def x (map (fn [x]
              (struct-map attendee
                :firstname x
                ;should be nil if not provided
                ;:lastname y
                :title :Mr
                :age 30))
            (map fixed-length-name-generator (repeat 10 10))))
(def firstname (accessor attendee :firstname))

(reduce (fn [a b] (conj a (:firstname b))) [] x)

;(map fixed-length-name-generator (repeatedly 10 (constantly 10)))

;Combining collections:
(into {:x 4} [{:a 1} {:b 2} {:c 3}])
;{:x 4, :a 1, :b 2, :c 3}

;Working with sets
(set/difference #{:1 :2 :3} #{:2})
(set/intersection #{:1 :2 :3} #{:2})
