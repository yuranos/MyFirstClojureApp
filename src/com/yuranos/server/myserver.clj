(ns com.yuranos.server.myserver
  (:require [clojure.tools.namespace.repl :refer [refresh]]))

(defn start
  "Start the application"
  [])

(defn stop
  "Stop the application"
  [])

(defn reset []
  (stop)
  (refresh :after 'com.yuranos.server.myserver/start))


(defn handler [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Hello World"})

(let [m {:x/a 1, :y/b 2}
      {:keys [x/a y/b]} m]
  (+ a b))

;=> (use 'ring.adapter.jetty)
;=> (use 'hello-world.core)
;=> (run-jetty handler {:port 3000})
