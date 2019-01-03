(ns com.yuranos.general.datatypes)

;Gen-class came first, followed by deftype in clojure 1.2.
; Deftype is preferred, and has better performance characteristics,
; but is more restrictive. A deftype class can conform to an interface,
; but cannot inherit from another class.

;proxy
;Each method fn takes an additional implicit
;first arg, which is bound to 'this.
(defn myproxy[& i]
  (proxy [clojure.lang.ISeq com.yuranos.javafiles.ShittyInterface][]
    (seq [] (sort i))
    (toString [] (apply str (interpose "-" i))))
    (printShit [] (prn "Shit")))

