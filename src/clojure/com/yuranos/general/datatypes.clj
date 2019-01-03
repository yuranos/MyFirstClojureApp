(ns com.yuranos.general.datatypes)

;Gen-class came first, followed by deftype in clojure 1.2.
; Deftype is preferred, and has better performance characteristics,
; but is more restrictive. A deftype class can conform to an interface,
; but cannot inherit from another class.

;proxy
;Each method fn takes an additional implicit
;first arg, which is bound to 'this.
(defn myproxy[& i]
  (proxy [;class must be first
          com.yuranos.javafiles.ShittyClass
          clojure.lang.ISeq
          com.yuranos.javafiles.ShittyInterface]["I'm useless"]
    (seq [] (sort i))
    (toString [] (apply str (interpose "-" i)))
    (printShit [] (prn "Shit"))
    (abstractMethod [] (prn (.getClassMission this)))) )
;Java stuff should be called with dot notation: (.printShit (myproxy 4 3 2 1))
