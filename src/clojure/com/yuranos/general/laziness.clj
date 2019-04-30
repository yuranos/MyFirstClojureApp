(ns com.yuranos.general.laziness)

(import java.util.UUID)


;Can be explicitly created with lazy-seq
(defn uuid-seq
  []
  (lazy-seq
    (cons (str (UUID/randomUUID))
          (uuid-seq))))

;However a lot of functions return lazy sequences:
;- map
;- filter
;- remove
;- range
;- take
;- take-while
;- drop
;- drop-while

;Realizing all values of Lazy Sequence:
(dorun (map inc [1 2 3 4]))
;= nil
(doall (map inc [1 2 3 4]))
;= (2 3 4 5)
;The difference between the two is that dorun throws away all results
;and is supposed to be used for side effects,
;while doall returns computed values.
;http://clojure-doc.org/articles/language/laziness.html
