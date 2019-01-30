(ns com.yuranos.general.varsandenvs)


[(type []) (class [])]
;[clojure.lang.PersistentVector clojure.lang.PersistentVector]

(with-redefs [type (constantly java.lang.String)
                     class (constantly 10)]
         [(type [])
          (class [])])
;[java.lang.String 10]


(defn ten [] 10)
;doall forces the seq to be realized.
;otherwise things like (def foo (map println [1 2 3])) will not print anything immediately.
;To be honest, in this particular case doall might not be needed. But see another example in .doc
(doall (pmap #(with-redefs [ten (fn [] %)] (ten)) (range 20 100)))
(ten)
;Never 10 any longer. Concurrent operation corrupts it somehow.
