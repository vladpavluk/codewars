(ns simpson.core)

(def a 0)
(def b Math/PI)

(defn- f[x]
  (-> (Math/sin x)
      (Math/pow 3)
      (* 1.5)))

(defn- h[n]
  (/ (- b a) n))

(defn- sum-all [fnc start end]
  (->> (range start (inc end))
       (map fnc)
       (reduce +)))

(defn simpson [n]
  (let [h (h n)
        left (sum-all #(f (+ a (* h (dec (* 2 %))))) 1 (/ n 2))
        right (sum-all #(f (+ a (* 2 % h))) 1 (dec (/ n 2)))]
    (-> (f a)
        (+ (f b))
        (+ (* 4 left))
        (+ (* 2 right))
        (* h)
        (/ 3))))