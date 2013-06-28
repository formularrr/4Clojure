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


;;27
;;Write a function which returns true if the given sequence is a palindrome.
((fn [x]
   (if (= (seq x) (reverse (seq x)))
     true
     false)) '(1 2 1))

;;29
;;Write a function which takes a string and returns a new string containing only the capital letters.
(#(apply str (re-seq #"[A-Z]+" %)) "SSSSsss")


;;33
;;Write a function which replicates each element of a sequence a variable number of times.
((fn [x y]
   (loop [rs '() element x]
     (if (empty? element) rs
           (recur (concat rs
                          (take y (repeat (first element))))
                  (rest element))))) [44 33] 2)



;;34
;;Write a function which creates a list of all integers in a given range.
((fn [x y]
   (loop [start x  rs '()]
      (if (= start y)
         (reverse rs)
         (recur (inc start) (conj rs start))))) -1 4)


;;38
;;Write a function which takes a variable number of parameters and returns the maximum value.
((fn [nu & x]
   (loop [max-number nu element x]
     (if (empty? element)
          max-number
         (if (< max-number (first element))
             (recur (first element) (rest element))
             (recur max-number (rest element)))))) 1 2 3 5 4)


;;42
;;Write a function which calculates factorials.
(#(reduce * (range 1 (+ % 1))) 5)


;;66
;;Given two integers, write a function which returns the greatest common divisor.
((fn [x y]
   (loop [ma (max x y) mi (min x y)]
     (if (zero? mi)
       ma
       (recur mi (rem ma mi))))) 1023 858)


;;89
;;Write a function which multiplies two numbers and returns the result as a sequence of its digits.
((fn [x y]
   (loop [rs (* x y) ve []]
     (if (zero? rs)
       ve
       (recur (quot rs 10) (cons (rem rs 10) ve ))))) 99 9)


;;134
;;Write a function which, given a key and map, returns true iff the map contains an entry with that key and its value is nil.
((fn [x se]
   (cond (not (contains? se x)) false
         (nil? (se x)) true
         :else false)) :b {:a nil :b 2})

;;156
;;When retrieving values from a map, you can specify default values in case the key is not found:
;;(= 2 (:foo {:bar 0, :baz 1} 2))
;;However, what if you want the map itself to contain the default values? Write a function which takes a default value and a sequence of keys and constructs a map.
((fn [ad se]
  (apply assoc {}
 (interleave (reverse se) (repeat ad)))) 0 [:a :b :c])