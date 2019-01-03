(ns com.yuranos.general.datatypes
  (:import (clojure.lang ArityException)))

;Gen-class came first, followed by deftype in clojure 1.2.
; Deftype is preferred, and has better performance characteristics,
; but is more restrictive. A deftype class can conform to an interface,
; but cannot inherit from another class.

;proxy
;Each method fn takes an additional implicit
;first arg, which is bound to 'this.

;If there is at least one implementation:
;ArityException Wrong number of args (2) passed to: datatypes/myproxy/fn--28  clojure.lang.AFn.throwArity (AFn.java:429)
;If there's none implementation:
;UnsupportedOperationException printParam  com.yuranos.general.datatypes.proxy$com.yuranos.javafiles.ShittyClass$ISeq$ShittyInterface$9bd1e475.printParam (:-1)

(defn myproxy[& i]
  (proxy [;class must be first
          com.yuranos.javafiles.ShittyClass
          clojure.lang.ISeq
          com.yuranos.javafiles.ShittyInterface]["I'm useless"]
    (seq [] (sort i))
    (toString [] (apply str (interpose "-" i)))
    (printShit ([] (prn "Shit"))
                ([str] (prn "Shit" str)))
    ;Without this: UnsupportedOperationException printParam
    ;com.yuranos.general.datatypes.proxy$com.yuranos.javafiles.ShittyClass$ISeq$ShittyInterface$9bd1e475.printParam (:-1)
    ;when called
    (printParam [param] (prn param))
    (abstractMethod ([] (prn (.getClassMission this)))
                    ([str] (prn (.getClassMission this) str))
      )
    ) )

;TODO: Reify
