(defproject com.clojurebook/my-first-clojure-project "1.0.0"
  :description "This is the simplest possible Leiningen project."
  :url "http://github.com/clojurebook/ClojureProgramming"
  :source-paths      ["src/clojure"]
  :java-source-paths ["src/java"]
  :main com.yuranos.general.datatypes
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-jetty-adapter "1.6.3"]
                 [org.clojure/tools.namespace "0.2.4"]
                 [spyscope "0.1.4"]]
  ;Can be used like (apply + #spy/p (range 11 20 2))
  :injections [(require 'spyscope.core)])
