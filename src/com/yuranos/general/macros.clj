(ns com.yuranos.general.macros)


(let [m {:1 1 :2 2 :3 3 :4 4}] (map list (keys m) (vals m)))
;((:1 1) (:2 2) (:3 3) (:4 4))

(defmacro not-really-macro [a] (do (println a) a))

(macroexpand `(not-really-macro "test"))

(not-really-macro "test")

;Working infix
(defmacro infix
  [infixed]
  (list (second infixed) (first infixed) (last infixed))
  )
;The problem here is with infixed evaluation: ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn
(defmacro infix-1
  [infixed]
  `((~second ~infixed) (~first ~infixed) (~last ~infixed))
  )
;Here the problem is with infixed: No such var: com.yuranos.general.macros/infixed
;Why doesn't it look up com.yuranos.general.macros/second?
(defmacro infix-2
  [infixed]
  `((second infixed) (first infixed) (last infixed))
  )
;Here the problem is with infixed: No such var: com.yuranos.general.macros/infixed
(defmacro infix-3
  [infixed]
  `((~second infixed) (~first infixed) (~last infixed))
  )
;This one is correct
(defmacro infix-4
  [infixed]
  `(~(second infixed) ~(first infixed) ~(last infixed))
  )
;TODO: ask Vova. ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn
;(defmacro infix
;  [infixed]
;  (((second infixed) (first infixed) (last infixed)))
;  )



(defmacro faulty-print
  [expression]
  `(list
    ~let [result expression]
        (list println result)
        result)
  )









;Need to ' quote the symbols you don't want to get the value from
(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))

;(defmacro my-print-whoopsie
;  [expression]
;  (list let [result expression]
;        (list println result)
;        result))

(defmacro unless
  "Similar to if but negates the condition"
  [condition & forms]
  `(if (not ~condition)
     ~@forms))

;(unless (= 1 2) "one does not equal two" "one equals two. How come?")
