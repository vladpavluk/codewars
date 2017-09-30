(ns longest_consec.core)
(require '[clojure.string :as str])

(defn- invalid? [st k]
  (let [n (count st)]
    (or (zero? n)
        (> k n)
        (<= k 0))))

(defmulti longest-cons invalid?)

(defmethod longest-cons true [_ _] "")

(defmethod longest-cons false [st k]
  (let [partitions
        (->> st
             (partition k 1)
             (map str/join))
        largest-count
        (->> partitions
             (sort-by count)
             reverse
             first
             count)]
    (->> partitions
         (filter #(= (count %) largest-count))
         first)))