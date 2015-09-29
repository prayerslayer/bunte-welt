(defproject bunte-welt "0.1.0-SNAPSHOT"
  :description "Converts Geo IP to RGB"
  :license {:name "MIT"
            :url "https://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [com.stuartsierra/component "0.3.0"]
                 [org.clojure/tools.logging "0.3.1"]
                 [clj-http-lite "0.3.0"]
                 [cheshire "5.5.0"]
                 [com.evocomputing/colors "1.0.3"]
                 [ring "1.4.0"]
                 [environ "1.0.1"]
                 [org.apache.logging.log4j/log4j-api "2.3"]
                 [org.apache.logging.log4j/log4j-core "2.3"]
                 [org.apache.logging.log4j/log4j-slf4j-impl "2.3"]
                 [org.apache.logging.log4j/log4j-jcl "2.3"]
                 [org.apache.logging.log4j/log4j-1.2-api "2.3"]
                 [org.apache.logging.log4j/log4j-jul "2.3"]]

  :main ^:skip-aot bunte-welt.core
  :target-path "target"
  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-ring "0.8.11"]]
                   :env {:http-port 3000}
                   :ring {:handler bunte-welt.handler/main}}})
