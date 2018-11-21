(ns com.yuranos.general.destruct)

(let [{a :a, b :b, c :c, :as m :or {a 2 b 3}}  {:a 5 :c 6}]
  [a b c m])
;[5 3 6 {:a 5, :c 6}]
;m - binds to the whole second {}, that is {:a 5, :c 6}

(let [m {:x/a 1, :y/b 2}
      {:keys [x/a y/b]} m]
  (+ a b))
;is the same as
(let [a 1 b 2]
  (+ a b))
