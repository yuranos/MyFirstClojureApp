(ns com.yuranos.general.var_args)

;This is called multi-arity function
(defn greet-multu-arity
  ([] (greet-multu-arity "you"))
  ([name] (print "Hello" name)))

;This is called variadic
(defn greet-variadic [name & rest] (print "Hello" name rest))
;Better to use with apply, which always unpacks sequences:
(apply println "hello" "there" ["you" "they"])
;hello there you they
(println "hello" "there" ["you" "they"])
;hello there [you they]
