(ns bunte-welt.interval)

(defn clamp
  [least greatest]
  (fn [x]
    (-> x
        (max least)
        (min greatest))))

(defn denormalize
  "Converts normalized number to range"
  [from to]
  (fn [x] ; x e [0..1]
    (float (+ (* from
              (- 1 x))
           (* x
              to)))))

(defn normalize
  "Converts number from domain to 0..1"
  [from to]
  (let [a (/ 1 (- to from))
        b (- 1 (* a to))]
    (fn [x]
      (-> x
          (* a)
          (+ b)
          (float)))))

(defn scale
  [domain range]
  (fn [x]
    (let [limit (apply clamp domain)
          down (apply normalize domain)
          up (apply denormalize range)]
      (-> x
          (limit)
          (down)
          (up)))))