(defn abs [x]
    (if (< x 0)
        (* -1 x)
        x))
;(abs 6)

(defn avg [x y]
  (/ (+ x y) 2))

;(avg 2 2)

(defn evalu [x y]
  (/ (/ x (+ (* y y)) (* 2 y)) 3))
;(evalu 64 4)

(defn good-enough? [x y]
  (if (<= (evalu x y) 1/6)
        true
        false))


(defn cube [x y]
  (if (good-enough? x y)
      y
      (cube x (+ y 0.01))))
