(ns bunte-welt.handler
  (:require [bunte-welt.interval :as interval]
            [bunte-welt.geo :as geo]
            [com.evocomputing.colors :as color]
            [clojure.tools.logging :as log])
  (:use ring.middleware.resource
        ring.util.response
        ring.middleware.content-type
        ring.middleware.not-modified))

(def hue-scale
  (interval/scale [-180 180]
                  [   0 360]))

(def light-scale
  (interval/scale [-80  80]
                  [  0 100]))

(defn color-at
  [{lat :latitude lng :longitude}]
  (let [c (color/create-color {:h (hue-scale lat)
                               :l (light-scale lng)
                               :s 100.0})]
    (color/rgb-hexstr c)))

(defn rand-ip
  []
  (->> (range 4)
       (map (fn [x] (rand-int 255)))
       (interpose ".")
       (apply str)))

(defn generate-html
  [location color]
  (str "<!DOCTYPE html>"
       "<html lang='en'>"
       "<head>"
         "<title>Bunte Welt</title>"
         "<link rel='stylesheet' href='/style.css' />"
         "<style>"
          "* { box-sizing: border-box; }"
          "html { height: 100%; }"
          "body {"
            "margin: 0;"
            "height: 100%;"
            "background: " color ";"
            "font-family: Helvetica, Arial, sans-serif;"
          "}"
         "</style>"
       "</head>"
       "<body>"
         "<main>"
          "<span>" color "</span>"
         "</main>"
       "</body>"
       "</html>"))

(defn colorizer
  [request]
  (let [ip (or (:remote-addr request)
               (rand-ip))
        location (geo/where-is ip)
        color (color-at @location)]
    (do
      (log/info "IP: %s" ip)
      (log/info "COLOR: %s" color)
      (log/info "LOCATION: %s" @location)
      (-> (generate-html location color)
          (response)
          (content-type "text/html")))))

(def main
  (-> colorizer
      (wrap-resource "public")
      (wrap-content-type)
      (wrap-not-modified)))