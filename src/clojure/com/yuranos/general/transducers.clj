(ns com.yuranos.general.transducers)

;Based on the article: https://eli.thegreenplace.net/2017/reducers-transducers-and-coreasync-in-clojure/
;What we want to do more effectively
(def s (range 0 10))
(reduce + (map inc (filter even? s)))

(defn mapping-transform
  [mapf]
  (fn [reducingf]
    (fn [acc item]
      (reducingf acc (mapf item)))))
(reduce ((mapping-transform #(* % %)) +) 0 s)
;285

(defn filtering-transform
  [predicate]
  (fn [reducingf]
    (fn [acc item]
      (if (predicate item)
        (reducingf acc item)
        acc))))
(reduce ((filtering-transform even?) +) 0 s)
;20

;And all together
(reduce ((filtering-transform even?) ((mapping-transform inc) +)) 0 (range 0 10))
;Same as
(reduce ((comp (filter even?) (map inc)) +) 0 (range 0 10))


;Most important operations:
into -
transduce -
cat -
completing -
comp -
mapcat - similar to flatMap
;; Used without a collection, filter will create a transducer:
(def xf (filter odd?))

;; We can now apply this transducer to a sequence:
(transduce xf conj (range 10))
;; => [1 3 5 7 9] - not a list, vector. (filter odd? (range 10)) will return a list.

;This
(def xform
  (comp
    (partial filter odd?)
    (partial map #(+ 2 %))))
(reduce + (xform (range 0 10)))
;Is the same as this
(def xform
  (comp
    (map #(+ 2 %))
    (filter odd?)))
(transduce xform + (range 0 10))
;Both return 35

;MAPCAT
;Take elements of outer collection one by one, apply reverse to them,
;but afterwards put them all in one collection instead of many
(mapcat reverse [[3 2 1 0] [6 5 4] [9 8 7]])
;(0 1 2 3 4 5 6 7 8 9)
;Without mapcat:
(concat (map reverse [[3 2 1 0] [6 5 4] [9 8 7]]))
;((0 1 2 3) (4 5 6) (7 8 9))

(map reverse [[3 2 1 0] [6 5 4] [9 8 7]])
(concat '((0 1 2 3) (4 5 6) (7 8 9)))

;INTO
(into [] (comp cat cat (map inc)) [[[1] [2]] [[3] [4]]])

(map
  (comp - (partial + 3) (partial * 2))
  [1 2 3 4])

(map #(* 2 %) [1 2 3 4])
;Same as:
(map (partial * 2) [1 2 3 4])




(map (comp - (partial * 2)) [1 2 3 4])

;TODO: No idea what that means:
;comp is the transducer equivalent to thrush
;reduce with a transformation of f (xf).
; If init is not supplied, (f) will be called to produce it.
; f should be a reducing step function that accepts both 1 and 2 arguments,
; if it accepts only 2 you can add the arity-1
; with 'completing'. - WHAT THE HELL IS THAT?
(transduce
  (comp
    (map #(* % %))
    (filter even?)
    (take 10)) + 0 (range))

(transduce + 0 (range 10))
(reduce + 0 (range 10))



