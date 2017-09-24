(ns revrot.core)
(require '[clojure.string :as str])

(defn- dispatch [str sz]
  (let [cnt (count str)]
    (when (or (= cnt 0)
              (< cnt sz)
              (<= sz 0))
      :empty)))

(def gen-partitions
  (comp (partial map clojure.string/join)
        partition))
(defn rotate-left [str]
  (clojure.string/join (concat (next str) [(first str)])))
(defn rev [str]
  (clojure.string/join (reverse str)))

(defn even-sum-of-cubes?
  "don't blame me for not using \"even\" or smth like,
  just want to use \"transduce\" first time in my life"
  [input]
  (let [check-even (fn [answer & _](not answer))
        xf (filter #(contains? #{\1 \3 \5 \7 \9} %))]
    (transduce xf check-even false input)))

(defmulti revrot dispatch)
(defmethod revrot :empty [_ _] "")
(defmethod revrot nil [string sz]
  (->> string
       (gen-partitions sz)
       (map (fn [part]
              (if (even-sum-of-cubes? part)
                (rev part)
                (rotate-left part))))
       (clojure.string/join)))

