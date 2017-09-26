(ns divpar7.core)

(defn next-match [n]
  (if (< n 100)
    [n]
    (let [next (-> n (/ 10) bigint (- (*' 2 (mod n 10))))]
      (lazy-seq (cons n (next-match next))))))

(defn seven [m]
  (let [res (next-match m)]
    [(last res) (dec (count res))]))