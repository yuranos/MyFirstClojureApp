(ns com.yuranos.general.namespaces
  (:import java.util.Date
           (clojure.java.api Clojure))
    (:require [clojure.java.io :as jio])
  (:require [ring.middleware.reload :refer [wrap-reload]])
  (:use [clojure.string :only (join)])
  (:use [ring.middleware.reload :only [wrap-reload]])
  )

;Update (4/18/2012): As of the 1.4.0 release, there's no longer a good reason
; to use use. Use require :refer instead. From the Clojure 1.4.0 changelog:
; "require can now take a :refer option. :refer
; takes a list of symbols to refer from the namespace or :all to bring in all public vars."

(refer 'clojure.string :only '[capitalize trim])
(capitalize (trim " hOnduRAS  "))
;"Honduras"

((-> "first" symbol resolve) [1 2 3])
;=> (resolve 'first)
;#'clojure.core/first

(in-ns 'first-namespace)
;com.yuranos.general.seq-namespace=> (in-ns 'first-namespace)
;#object[clojure.lang.Namespace 0x4abab45b "first-namespace"]
;first-namespace=> (def my-var "some value")
;#'first-namespace/my-var
