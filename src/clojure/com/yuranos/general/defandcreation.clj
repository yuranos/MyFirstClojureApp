(ns com.yuranos.general.defandcreation
  (:import (clojure.java.api Clojure)
           (com.sun.org.apache.xalan.internal.xsltc.compiler If)))

;Cleaning up namespace
;(map #(ns-unmap *ns* %) (keys (ns-interns *ns*)))
(def mymap {:a 1 :b 2})
(deftype Person [fname lname])
(def dotPerson (Person. "Yura" "Nosenko"))
(def newPerson (new Person "Yura" "Nosenko"))
(def factoryPerson (->Person "Yura" "Nosenko"))

;defrecords and defprotocols
; Interface
;From doc: defprotocol is dynamic, has no special compile-time  effect,
; and defines no new types or classes.

(defprotocol P (fooMethod [this]) (bar-me [this] [this y]))
(deftype FooType [a b c]  P (fooMethod [this] a) (bar-me [this] b) (bar-me [this y] (+ c y)))
(bar-me (FooType. 1 2 3) 42)
;=> 45
(fooMethod (FooType. 2 4 6))
;=> 2
(bar-me (let [x 42] (reify P  (fooMethod [this] 17)
                    (bar-me [this] x) (bar-me [this y] (/ x y)))) 2)
;=> 21


;Extend BEGIN
(defprotocol Bazzer
  "This is an interface that states a `baz` method should be implemented"
  (baz [this] [a b])
  (mymethod [this] [a b])) ; you can define multi arity interface, but seemingly can't implement it on a defrecord?
; instead use `extend-protocol` for those situations
; see following example

;yuranos: can be used in lieu of extend-type
; but can represent multiple types (Can be more than Integer)
;(extend) is also very similar but more verbose - you need to specify a map with keywords
;Here we basically extent every single Long we will use from now on.
(extend-protocol Bazzer
  Long
  (baz
    ([a] 1)
    ([a b] 2))
  (mymethod
    ([a b] 100)))

(prn (baz 12323))
; 1
(prn (baz 12312 123123))
; 2
;BEST PRACTICE
;By Rich Hickey:
;If a protocol comes with Clojure itself, avoid extending it to types
;you don't own, especially e.g. java.lang.String and other core Java
;interfaces. Rest assured if a protocol should extend to it, it will,
;else lobby for it.
;https://groups.google.com/forum/#!msg/clojure/vyX5-F3NiVg/Ti1apkxDFl0J

;(extend-type Bazzer
;  Integer ; the return type determines if symbols referenced (e.g. a and b) can be resolved
;  ; if not defined (or the wrong type) then errors can occur
;  (baz
;    ([a] 1)
;    ([a b] 2))
;  (mymethod
;    ([a b] 100)))

;Extend END


; Constructor
(defrecord Foo [a b]
  Bazzer ; enforces the interface (but the error thrown without this defined,
  ; doesn't actually clarify)
  (baz [this] "Foo Bazzer")) ; associate a function with our `defrecord` map

; Constructor
(defrecord Bar [a b] Bazzer
  (baz [this] (str "Bar Bazzer -> " a " / " b)))

; Either pass in each argument to the constructor separately
;-> means a default constructor
(def foo (->Foo :bar :baz)) ; user.Foo{:a :bar, :b :baz}

; Or pass a single argument with keys that align with the class' required parameters
(def bar (map->Bar {:a 1 :b 2})) ; user.Foo{:a 1, :b 2}

(prn bar)      ; user.Foo{:a 1, :b 2}
(prn (:a foo)) ; :bar
(prn (:b foo)) ; :baz

; mutate and return
(prn (assoc foo :b :qux)) ; user.Foo{:a :bar, :b :qux}

; but the source is immutable
(prn foo) ; user.Foo{:a :bar, :b :baz}

(:b (assoc foo :b :qux)) ; :qux

(def basil (Foo. "hai" "hi"))
(def baril (Bar. "boo" "yah"))
(prn (baz basil)) ; "Foo Bazzer""
(prn (baz baril)) ; "Bar Bazzer -> boo / yah""
; (prn (baz baril :a :b))


;EXTEND VIA METADATA
(defprotocol Component
  ;the name of the protocol doesn't matter.
  ;It seems the only thing that does matter is method declaration.
  :extend-via-metadata true
  (start [component]))
(def component (with-meta {:name "whatever"} {`start (constantly "started")}))
(start component)


(defprotocol P (report [x]))
(report (with-meta [1 2 3] {`report (constantly "yo")}))

;(defn -main
;  [& args]
;  (println (map + [1 2 3] [4 5 6] [7 8 9])))

;yuranos: my own defrecord example
(defrecord FooNoInt [a b])
(def fni (FooNoInt. "this is a" "this is b"))
(def fnihashed #com.yuranos.general.defandcreation.FooNoInt[:hey :ho])   ;for defrecords and deftypes only
;#com.yuranos.general.general.FooNoInt{:a :hey, :b :ho}

(defn myfunction
  ([a b]   (myfunction a b 100))
  ([a b c] (* a b c)))

(defn -main
  [& args]
  (println "List of namespaces: "(ns-interns *ns*))
  (println "Keys from mymap: "(mymap :a)(:b mymap))
;  (println "And here is my Set: "#{:a :b :c :a}) - won't work with a duplicate
  (println "Get fname and lname of a person:" (.fname dotPerson) (. dotPerson lname))
  (println "Get fname and lname of a person:" (. dotPerson fname) (. dotPerson lname))
  (println "Get fname and lname of a person(newPerson):" (. newPerson fname) (. newPerson lname))
  (println "Get fname and lname of a person(factoryPerson):" (. factoryPerson fname) (. factoryPerson lname))
  (println "Let example: " (let [x 2, y 3, z 4] (= 9 (+ x y z))))
  (println "Let example: " (let [x 2, y 3, z 4] (identical? 9 (+ x y z))))
  )
