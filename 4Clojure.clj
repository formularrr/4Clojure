;;#21
;;Write a function which returns the Nth element from a sequence.
((fn [x y]
   (loop [element x cnt y]
     (if (= cnt 0)
       (first element)
       (recur (rest element) (- cnt 1))))) '(4 5 6 7) 2)



;;#22
;;Write a function which returns the total number of elements in a sequence.

((fn [x]
   (loop [element x cnt 0]
     (if (empty? element)
       cnt
       (recur (rest element) (+ cnt 1))))) [[12]])


;;23
;;Write a function which reverses a sequence.

((fn [x]
   (loop [element x acc nil]
     (if (empty? element)
       acc
       (recur (rest element) (conj acc (first element)))))) [[1 2][3 4]])


;;24
;;Write a function which returns the sum of a sequence of numbers.

((fn [x]
   (loop [element x rs 0]
     (if (empty? element)
       rs
       (recur (rest element) (+ rs (first element)))))) '(0 0 -1))


;;25
;;Write a function which returns only the odd numbers from a sequence.

(filter #(not( even? %))'(1 2 3 4))


;;26
;;Write a function which returns the first X fibonacci numbers.
((fn [x]
   (loop [element '(1 1) cnt 2]
     (cond  (= 1 x) '(1)
            (= 2 x) '(1 1)
            (= cnt x) (reverse element)
            :else
            (recur (cons (+ (nth element 0) (nth element 1)) element)
                   (+ cnt 1)))))
       8)


;;29
;;Write a function which takes a string and returns a new string containing only the capital letters.
(#(apply str (re-seq #"[A-Z]+" %)) "SSSSsss")