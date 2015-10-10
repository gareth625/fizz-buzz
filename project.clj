(defproject fizz-buzz "0.1.0-SNAPSHOT"
  :description "Playing with a fizz-buzz solution in Clojure inspired by: https://twitter.com/mattmcd/status/648583053795848192."
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main ^:skip-aot fizz-buzz.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
