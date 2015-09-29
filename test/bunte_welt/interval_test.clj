(ns bunte-welt.interval-test
  (:require [clojure.test :refer :all]
            [bunte-welt.interval :as interval]))

(deftest normalize-1
  (let [norm (interval/normalize 4 12)]
    (is (= (norm 8)
           0.5))))

(deftest denormalize-1
  (let [denorm (interval/denormalize 4 12)]
    (is (= (denorm 0.5)
           8.0))))
