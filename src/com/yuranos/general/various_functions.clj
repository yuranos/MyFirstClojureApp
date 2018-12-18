(ns com.yuranos.general.various-functions)

;By default, pr and prn print in a way that objects can be read by the reader,
;while print and println produce output for human consumption.
;Nothing in REPL
(Thread/sleep 2000)
(print "Hello print")

;Prints to REPL
;(prn "Hello prn")
;Used together they both print: Hello print"Hello prn"

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

(re-seq #"[0-9]+" "abs123def345ghi567")
;("123" "345" "567")
(re-find #"([-+]?[0-9]+)/([0-9]+)" "22/7")
;["22/7" "22" "7"]
(let [[a b c] (re-matches #"([-+]?[0-9]+)/([0-9]+)" "22/7")] [a b c])
;["22/7" "22" "7"]
(re-seq #"(?i)[fq].." "foo bar BAZ QUX quux")
;("foo" "QUX" "quu")


