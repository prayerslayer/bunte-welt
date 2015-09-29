(ns bunte-welt.geo
  (:require [cheshire.core :as json]
            [clj-http.lite.client :as curl]))

(defn where-is
  [ip]
  (future (-> (curl/get (str "https://api.userinfo.io/userinfos?ip_address=" ip))
              (:body)
              (json/decode true)
              (:position)
              ; default to berlin if nil
              (#(if (nil? (:latitude %))
                    {:latitude  52.5208317
                     :longitude 13.4096191}
                    %)))))