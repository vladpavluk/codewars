(ns opstrings.core)
(require '[clojure.string :as str])

(defn- get-strings [s]
  (str/split s #"\n"))

(defn- apply-changes [func strng]
  (->> strng
       get-strings
       func
       (str/join "\n")))

;DRY is not fully applied here, maybe
;there could be a good idea to write this macros-based
;but let's retain functional style
(def vert-mirror
  (partial
    apply-changes
    (partial map (comp str/join reverse))))

(def hor-mirror
  (partial apply-changes reverse))

(defn oper [fct s]
  (fct s))