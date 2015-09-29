(ns bunte-welt.core
  (:require [bunte-welt.http :as http]
            [environ.core :refer [env]]
            [com.stuartsierra.component :as component])
  (:gen-class))

(defn- run
  []
  (let [system (component/system-map :http (http/new-http (env :http-port)))]
    (component/start system)))
  

(defn -main
  []
  (try
    (run)
    (catch Exception ex
      (println (pr-str ex)))))
