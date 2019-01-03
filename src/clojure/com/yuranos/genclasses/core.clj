(ns com.yuranos.genclasses.core
  (:gen-class)
  (:require [com.yuranos.genclasses.genclassexample :as mygenclass])
  (:require [com.yuranos.genclasses.geninterface :as mygeninterface])
  (:require [com.yuranos.genclasses.names :as names]))
(gen-class
  :name   myfirstclojure.core.ClassA
  :prefix classA-)

(gen-class
  :name   myfirstclojure.core.ClassB
  :prefix classB-)

(defn classA-toString
  [this]
  "I'm an A.")

(defn classB-toString
  [this]
  "I'm a B.")

;Works as expected
(defn -main
  "I don't do a whole lot...yet."
  [& args]
  (println "Hello, World!" (.toString (myfirstclojure.core.ClassA.))))

;This actually works without trouble
;(defn -main
;  "I don't do a whole lot...yet."
;  [& args]
;     (println "Hello, World!" (names/familiar-name "sam" "bob")))

;user=> (def ex (com.example.Demo.))
;user=> (def ex (myfirstclojure.com.yuranos.genclassexample.Demo.)) - ClassNotFound
;user=> (-getLocation (myfirstclojure.com.yuranos.genclassexample.Demo.)) - ClassNotFound
;#'user/ex
;user=> (.getLocation ex)
;"default"
;user=> (.setLocation ex "time")
;nil
;user=> (.getLocation ex)
;"time"
