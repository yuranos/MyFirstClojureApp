(ns com.yuranos.general.agents)

(def foo-agent (agent 1))

;defn has an implicit do: http://clojure-doc.org/articles/language/glossary.html#implicit-do
(defn slow-inc-agent [n]
  (Thread/sleep 2000)
  5)

;Without sleeps will return three 1s.
(do
  (send foo-agent slow-inc-agent)
  (send foo-agent slow-inc-agent)
  (println @foo-agent)
  (Thread/sleep 2500)
  (println @foo-agent)
  (Thread/sleep 2500)
  (println @foo-agent))
