(ns com.yuranos.general.multi)

(declare do-a-thing-with-a-long)
(declare do-a-thing-with-a-string)

(defn do-a-thing
  "Do a thing to a String or a Long"
  [in]
  (cond
    (instance? String in)
    (do-a-thing-with-a-string in)
    (instance? Long in)
    (do-a-thing-with-a-long in)))

(defn do-a-thing-with-a-string "I'm a String" [in]
   in)

(defn do-a-thing-with-a-long "I'm a Long" [in]
   in)

;1
;Multimethods - you will only need to change things once
(defmulti do-a-thing-multi class)
(defmethod do-a-thing-multi String [in]
  (prn "I'm a String" in))
(defmethod do-a-thing-multi Long [in]
  (prn "I'm a Long" in))

;2
(defmulti greeting :lang)
(defmethod greeting "English" [_] "Hi" _)
(defmethod greeting "French" [_] "Salut" _)
;(greeting {:lang "English"})

;3
;(defmulti greetingfn
;          (fn[{:keys [language] :strs}] ()))
(defmulti greetingfn
          (fn[x] (x "language")))
(defmethod greetingfn "English" [_]
  "Hello!")
(defmethod greetingfn "French" [_]
  "Bonjour!")
(defmethod greetingfn :default [params]
  (throw (IllegalArgumentException.
           (str "I don't know the " (params "language") " language"))))
;(def english-map {"id" "1", "language" "English"})
;(def  french-map {"id" "2", "language" "French"})
;(def spanish-map {"id" "3", "language" "Spanish"})


;4
(defmulti factorial identity)
(defmethod factorial 0 [_]  1)
(defmethod factorial :default [num]
  (* num (factorial (dec num))))

;TODO: ask Vova
;(defmulti service-charge (fn [acct] [(account-level acct) (:tag acct)]))

;5
(defmulti bat
          (fn ([x y & xs]
               (mapv class (into [x y] xs)))))
(defmethod bat [String String] [x y & xs]
  (str "str: " x " and " y))
(defmethod bat [String String String] [x y & xs]
  (str "str: " x ", " y " and " (first xs)))
(defmethod bat [String String String String] [x y & xs]
  (str "str: " x ", " y ", " (first xs) " and " (second xs)))
(defmethod bat [Number Number] [x y & xs]
  (str "number: " x " and " y))

;6
(defmulti x (fn[_] :dec))
(defmethod x :inc [y] (inc y))
(defmethod x :dec [y] (dec y))
(x 0) ;; => 1
;(defmulti x (fn[_] :dec)) ;; Can't redefine :(
(x 0) ;; => 1 ;; STILL :(
;(ns-unmap *ns* 'x) ;; => unmap the var from the namespace
;(defmulti x (fn[_] :dec))
(x 0) ;; => Exception, we now need to redefine our defmethods.


(defn -main
  [& args]
  (prn (type args))
  (do-a-thing (first args)))

;Just some exercising with defn
(defn bar [a b & [c]]
  (if c
    (* a b c)
    (* a b 100)))

(defn foo
  ([x y]
   (println "two arguments")
   (+ x y))
  ([x y z]
   (println "three arguments!")
   (+ x y z)))


;metadata
(defn greet {:tag String :private true} [name] (format "Hello, %s" name))
;Use meta to get the data
(meta #'greet)
