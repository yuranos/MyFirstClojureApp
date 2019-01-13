(ns com.yuranos.general.transients)


;Bad
;It seems the reason is that we do not work with the return type of assoc!
;and instead try to address m. But m will only contain first 8 values from the range
;and for the rest of them assoc! will create a new data structure with a new pointer.
(count
  (let [m (transient {})]
    (dotimes [i 1000000]
      (assoc! m i i)) (persistent! m)))

;Good
;Important tenet:
;the original collection you passed in should be treated as having an undefined value.
; Only the return value is predictable.
;So, once transient is applied to something,
; forget about it and focus on extracting the rerun type instead.
(count
  (let [m (transient {})]
    (persistent!
      (reduce (fn [acc i] (assoc! acc i i))
              m (range 1000000)))))

(def mycol [1 2 3])
(assoc! (transient mycol) 0 10)

;"Bashing in place"
;Can cause serious problems.
(let [my-map (transient {:x 1 :y 2 :z 3})]
  (dissoc! my-map :x)   ; mistake is to use my-map below, not dissoc! return val
  (persistent! my-map)) ; returns persistent map {:y 2 :z 3}

;Correct
(let [my-map (transient {:x 1 :y 2 :z 3})
      x (dissoc! my-map :x)]    ; after this, don't use my-map again, only x
  (persistent! x))    ; returns persistent map {:y 2 :z 3}
