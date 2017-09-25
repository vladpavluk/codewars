(ns carboat.core)
(require '[clojure.string :as str])

(defn- get-result-if-c-integer [b]
  ;1 + 7B should be dividable by nine
  (let [c (-> b (* 7) inc (/ 9))]
    (when (integer? c)
      {:C c
       :B b
       :M (inc (* 9 c))})))

(defn- calc-min-max-for-b [m n]
  (when (and m n)
    [(-> (min m n) (- 2) (/ 7) Math/floor int inc)
     (-> (max m n) (- 2) (/ 7) Math/floor int inc)]))

(defn- prepare-result [maps]
  (->> maps
       (sort-by :M)
       (mapv
         (fn [{:keys [M B C]}]
           [(str "M: " M) (str "B: " B) (str "C: " C)]))))

(defn howmuch [m n]
  (->> (for [let-b (apply range (calc-min-max-for-b m n))]
         (get-result-if-c-integer let-b))
       (filter identity)
       prepare-result))