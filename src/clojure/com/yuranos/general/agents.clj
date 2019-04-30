(ns com.yuranos.general.agents)

(def foo-agent (agent 1))

(defn slow-inc [n]
  (swap! counter inc)
  (Thread/sleep 2000)
  (inc n))

(do
  (send foo-agent slow-inc)
  (send foo-agent slow-inc)
  (println @foo-agent)
  (Thread/sleep 2500)
  (println @foo-agent)
  (Thread/sleep 2500)
  (println @foo-agent))
