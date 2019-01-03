(ns com.yuranos.general.various-functions)

;Repeating items or infinite
(repeat 5 "x")
(take 5 (repeat "x"))

;To use as a function:
(take 5 (repeatedly #(rand-int 2)))
;(0 0 1 0 1)

;Repeatedly takes a function, repeat takes a value:
(repeatedly 3 #(rand-int 2))
;(1 0 1)
(repeatedly 3 (rand-int 2))
;ClassCastException java.lang.Integer cannot be cast to clojure.lang.IFn
(repeat 3 (rand-int 2))
;(1 1 1)

;Function composition:
((comp :b :a) {:a {:b 1} :b 2})
;1
;juxt will not pipe the arguments, it will apply the same arguments sequentially to every single function
((juxt :a :b) {:a {:b 1} :b 2})
;[{:b 1} 2]

;constantly - always return the same thing
(def boring (constantly 10))
(boring)
(boring :key)
(boring "Hello there")
(boring 123)

;Arithmetic
(== (int 1) (double 1))
;true
(= (int 1) (double 1))
;false

;Regex
;re-pattern - Returns an instance of java.util.regex.Pattern
(= (re-find (re-pattern "\\d+") "abc123def") (re-find #"\d+" "abc123def"))
;true

;Can't pass a matcher to re-seq, only re and s
(re-seq #"[0-9]+" "abs123def345ghi567")
;("123" "345" "567")
;But can pass a matcher to re-find
(re-find #"([-+]?[0-9]+)/([0-9]+)" "22/7")
;["22/7" "22" "7"]
(let [[a b c] (re-matches #"([-+]?[0-9]+)/([0-9]+)" "22/7")] [a b c])
;["22/7" "22" "7"]
