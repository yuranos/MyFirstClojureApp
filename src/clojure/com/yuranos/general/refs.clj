(ns com.yuranos.general.refs
  (:require [clojure.core.async :as async]))

(def foo (ref 0))
(def bar (ref 0))

(async/go
 (dotimes [_ 10]
   (dosync
    (alter foo inc)
    (Thread/sleep 1000)
    (alter bar inc)
    (println "updated, foo:" @foo "bar:" @bar))))

(async/go
 (dotimes [_ 10]
   (dosync
    (println "read, foo:" @foo "bar:" @bar))
   (Thread/sleep 1000)))
