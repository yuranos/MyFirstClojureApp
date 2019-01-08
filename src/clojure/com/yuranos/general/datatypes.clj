(ns com.yuranos.general.datatypes
  (:import (clojure.lang ArityException)
           (clojure.java.api Clojure)))

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
;UnsupportedOperationException printParam  com.yuranos.general.datatypes.proxy$com.yuranos.javafiles.ClassWithMission$ISeq$InterfaceWithDefaultMethod$9bd1e475.printParam (:-1)

(defn myproxy[& i]
  (proxy [;class must be first
          com.yuranos.javafiles.ClassWithMission
          clojure.lang.ISeq
          com.yuranos.javafiles.InterfaceWithDefaultMethod] ["I'm useless"]

    ;For ISeq
    (seq [] (sort i))
    (toString [] (apply str (interpose "-" i)))

    ;For com.yuranos.javafiles.InterfaceWithDefaultMethod
    (printStatic ([] (com.yuranos.javafiles.InterfaceWithDefaultMethod/myStaticMethod)))
    ;Seems like default methods are not supported: UnsupportedOperationException myDefaultMethod
    (printSomethingOrDefault ([] (.myDefaultMethod this))
                ([str] (prn "printSomethingOrDefault called with arg:" str)))
    ;Without this: UnsupportedOperationException printParam
    ;com.yuranos.general.datatypes.proxy$com.yuranos.javafiles.ClassWithMission$ISeq$InterfaceWithDefaultMethod$9bd1e475.printParam (:-1)
    ;when called
    (printParam [param] (prn param))
    (myDefaultMethod [] (prn "Can't call default, but can override it. Welcome to Clojure!"))

    ;For com.yuranos.javafiles.ClassWithMission
    ;You can override your mission no problem.
    (getClassMission [] (prn "Mission has been overridded. Be careful."))
    (abstractMethod ([] (prn (.getClassMission this)))
                    ([str] (prn (.getClassMission this) str))
      )
    ) )
;Proxy methods are called like:
;(.printSomethingOrDefault (myproxy 4 3 2 1) "Param")
;unless we are dealing wih purely Clojure contracts (like seq)

;TODO: Reify
