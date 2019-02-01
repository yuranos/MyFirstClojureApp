(ns com.yuranos.general.shared_state)

;software transactional memory (STM)
;It is an alternative to lock-based synchronization.
;Another feature common to STMs is that,
; should a transaction have a conflict while running, it is automatically retried.

;Will also look into Atoms, Agents, Refs

;Atoms: synchronous change
;Will loop over multiple times (re-read the value before changing it)
; and retry the change until it succeed

(def counter1 (atom 0))
(def counter2 (atom 0))
(def foo (atom 1))
;The only thing to grasp here is that swap will call
;supplied inc function potentially more than 100 times (range 100)
(defn slow-inc
  ([n counter incFunc]
   (swap! counter incFunc counter2)
    ;without sleep the counter is a lot smaller in the end(fewer re-tries).
    (Thread/sleep 100)
   ;But increment the value, check if it hasn't changed
   ; in memory and if not apply, otherwise - retry
   (inc n))
  ([n counter]
   (swap! counter inc)
    (Thread/sleep 100)
   (inc n)))

(pmap
  (fn [_]
    ;swap will be called 100 times,
    ;slow-inc will be called more than that
    (swap! foo slow-inc counter1 slow-inc))
  (range 100))

;--------------------------------------------
;Weird bug in with-redeft when combined with pmap
(defn ten [] 10)
(doall (pmap #(with-redefs [ten (fn [] %)] (ten)) (range 20 100)))


;----------------------------------------------
;AGENTS
;Async calls, functions will end up in a queue,
;never re-tries
(defn slow-inc [n val]
  ;(swap! counter inc)
  ;(Thread/sleep 2000)
  (set! n val))

;(do
  ;(send foo-agent slow-inc)
  ;(send foo-agent slow-inc)
  ;(println @foo-agent)
  ;Without sleeps here will return
  ;3 exact same values(1 with sleep in slow-counter, 1 without)
  ;(Thread/sleep 500)
  ;(println @foo-agent)
  ;(Thread/sleep 500)
  ;(println @foo-agent))
(def foo-agent (agent 1))
(def counter (atom 0))
(pmap
  (fn [val]
    (send foo-agent #((swap! counter inc) (var-set %1 %2)) val))
  (range 100))

