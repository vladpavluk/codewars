(ns dubstep)
(require '[clojure.string :as str])

(defn song-decoder [song]
  (str/trim (str/replace song #"(WUB)+" " ")))