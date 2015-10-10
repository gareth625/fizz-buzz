(ns fizz-buzz.core
  (:gen-class))

(def kids-fizz-buzz-cases
  "The cases for the child friendly fizz-buzz."
  [[3 "fizz"] [5 "buzz"] [15 "fizz-buzz"]])

(defn fizz-buzz
  "Returns the mapping value from a fizz-buzz case."
  [fizz-buzz-case]
  (second fizz-buzz-case))

(defn fizz-buzz?
  "Takes a fizz-buzz mapping and checks to see if x is a match. Returns true if it is, false otherwise."
  [fizz-buzz-case x]
  ;; Wasn't sure how to deal with zero, really it's a divide by zero error but
  ;; I think that just means it's never a fizz-buzz case so returning false is
  ;; acceptable.
  ;; What would a five year old do?
  (if-not (= x 0)
    (= (mod x (first fizz-buzz-case)) 0)
    false))

(defn fizz-buzz-builder
  "Returns a function that given a number responds fizz-buzzes."
  [fizz-buzz-cases]
  (reduce (fn [acc fizz-buzz-case]
            (fn [x] (if (fizz-buzz? fizz-buzz-case x)
                     (fizz-buzz fizz-buzz-case)
                     (acc x))))
          identity
          fizz-buzz-cases))

(defn play-kids-fizz-buzz
  [coll]
  (map (fizz-buzz-builder kids-fizz-buzz-cases) coll))

;; ----
;; Play the game :D
(defn -main
  [& args]
  (println (play-kids-fizz-buzz (range -100 100))))

#_ (-main)

;; -----
;; Tests
;; kids-fizz-buzz-cases

;; (fizz-buzz (first kids-fizz-buzz-cases))

;; (map (partial fizz-buzz? (first kids-fizz-buzz-cases)) (range 10))

;; (# (if (fizz-buzz? (first kids-fizz-buzz-cases) %)
;;      (fizz-buzz (first kids-fizz-buzz-cases))
;;      %)
;;    3)

;; ((fn [x]
;;     (if (fizz-buzz? (second kids-fizz-buzz-cases) x)
;;       (fizz-buzz (second kids-fizz-buzz-cases))
;;       ((fn [y]
;;          (if (fizz-buzz? (first kids-fizz-buzz-cases) y)
;;            (fizz-buzz (first kids-fizz-buzz-cases))
;;            y)) x)))
;;  14)

;; ((fn [x]
;;     (if (fizz-buzz? (second kids-fizz-buzz-cases) x)
;;       (fizz-buzz (second kids-fizz-buzz-cases))
;;       ((fn [y]
;;          (if (fizz-buzz? (first kids-fizz-buzz-cases) y)
;;            (fizz-buzz (first kids-fizz-buzz-cases))
;;            y)) x)))
;;  5)

;; ((fizz-buzz-builder kids-fizz-buzz-cases) 3)
;; ((fizz-buzz-builder kids-fizz-buzz-cases) 5)
;; ((fizz-buzz-builder kids-fizz-buzz-cases) 15)
;; ((fizz-buzz-builder kids-fizz-buzz-cases) 14)
