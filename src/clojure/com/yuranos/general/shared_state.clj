(ns com.yuranos.general.shared_state)

;software transactional memory (STM)
;It is an alternative to lock-based synchronization.
;Another feature common to STMs is that,
; should a transaction have a conflict while running, it is automatically retried.

;Will also look into Atoms, Agents, Refs

;Atoms: synchronous change
;Will loop over multiple times (re-read the value before changing it)
; and retry the change until it succeed
(def counter (atom 0))
(def foo (atom 1))

(defn slow-inc [n]
  (swap! counter inc)
  ;without sleep the counter is a lot smaller in the end(fewer re-tries).
  (Thread/sleep 500)
  (inc n))

(pmap
  (fn [_]
    (swap! foo slow-inc))
  (range 100))


(defn ten [] 10)
(doall (pmap #(with-redefs [ten (fn [] %)] (ten)) (range 20 100)))
