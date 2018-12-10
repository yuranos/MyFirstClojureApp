(ns com.yuranos.general.macros
  (:import (com.sun.xml.internal.bind.v2 TODO)))


(let [m {:1 1 :2 2 :3 3 :4 4}] (map list (keys m) (vals m)))
;((:1 1) (:2 2) (:3 3) (:4 4))

(defmacro not-really-macro [a] (do (println a) a))

(macroexpand `(not-really-macro "test"))

;Working infix (1 + 2)
(defmacro infix
  [infixed]
  (list (second infixed) (first infixed) (last infixed))
  )
;The problem here is with infixed evaluation: ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn
(defmacro infix-1
  [infixed]
  (prn infixed)
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

(defmacro good-print
  [expression]
  (list
    'do
    (list 'println expression)
    expression)
  )

;Correct code
(defmacro my-print
  [expression]
  (list 'let ['result expression]
        (list 'println 'result)
        'result))

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



(defmacro code-critic1
  "Phrases are courtesy Hermes Conrad from Futurama"
  [bad good]
  (list 'do
        (list 'println
              "Great squid of Madrid, this is bad code:"
              (list 'quote bad))
        (list 'println
              "Sweet gorilla of Manila, this is good code:"
              (list 'quote good))))


;Calling this function in isolation will fail epically:
;(criticize-code "Cursed bacteria of Liberia, this is bad code:" (1 + 2))
;
;ClassCastException java.lang.Long cannot be cast to clojure.lang.IFn
;But inside macros it is Ok
(defn criticize-code
  [criticism code]
  `(println ~criticism (quote ~code)))

(defmacro code-critic
  [bad good]
  `(do ~@(map #(apply criticize-code %)
             [["Great squid of Madrid, this is bad code:" bad]
              ["Sweet gorilla of Manila, this is good code:" good]])))

;Validation implementation example
;(def order-details
;  {:name "Mitchard Blimmons"
;   :email "mitchard.blimmonsgmail.com"})
;
;(validate order-details order-details-validations)
;
;(def order-details-validations
;  {:name
;   ["Please enter a name" not-empty]
;
;   :email
;   ["Please enter an email address" not-empty
;
;    "Your email address doesn't look like an email address"
;    #(or (empty? %) (re-seq #"@" %))]})
