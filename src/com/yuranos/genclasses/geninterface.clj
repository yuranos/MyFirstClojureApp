(ns com.yuranos.genclasses.geninterface
  (:gen-class
    :implements [clojure.lang.IDeref]))

(defn -deref
  [this]
  "Hello, World! I'm a generated method for IDeref interface!")
