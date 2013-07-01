(ns solutions)

;;#21.Nth Element
;;Write a function which returns the Nth element from a sequence.
((fn [se n]
   (first (drop n se))) '(4 5 6 7) 2)



;;#22 Count a Sequence
;;Write a function which returns the total number of elements in a sequence.
;;first
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
(reduce + (list 0 -2 5 5))

;;25 Find the odd numbers
;;Write a function which returns only the odd numbers from a sequence.
(filter #(odd? %)'(1 2 3 4))


;;26
;;Write a function which returns the first X fibonacci numbers.
((fn [n]
   (take n
         (map first
              (iterate (fn [[a b]] [b (+ a b)]) [1 1]))))
 3)


;;27
;;Write a function which returns true if the given sequence is a palindrome.
((fn [x]
   (if (= (seq x) (reverse (seq x)))
     true
     false)) '(1 2 1))


;;28
;;Write a function which flattens a sequence
(#(filter (complement sequential?)
          (rest (tree-seq sequential? seq %))) '(1 2 3))


;;29
;;Write a function which takes a string and returns a new string containing only the capital letters.
(#(apply str (re-seq #"[A-Z]+" %)) "SSSSsss")


;;30
;;Write a function which removes consecutive duplicates from a sequence.
((fn [n]
   (loop [rs '() ori (seq n)]
     (if (empty? (rest ori))
       (reverse (conj rs (first ori)))
       (if (= (first ori) (fnext ori))
         (recur rs (rest ori))
         (recur (conj rs (first ori)) (rest ori)))))) [1 1 2 2 4 4 3])


;;31 Pack a sequence
;;Write a function which packs consecutive duplicates into sub-lists.
(partition-by identity [1 1 2 1 1 1 ])


;;32
;;Write a function which duplicates each element of a sequence.
((fn [n]
   (loop [rs '() ori n]
     (if (empty? ori)
       rs
       (recur (concat rs (take 2 (repeat (first ori)))) (rest ori))))) '([1 3] s))


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

;;39 Interleave Two Seqs
;;Write a function which takes two sequences and returns the first item from each, then the second item from each, then the third, etc.
((fn inter [c1 c2]
   (lazy-seq
    (let [s1 (seq c1) s2 (seq c2)]
      (when (and s1 s2)
        (cons (first s1)
              (cons (first s2)
                    (inter (rest s1) (rest s2))))))))
  [1 2] [2])


;;40 Interpose a Seq
;;Write a function which separates the items of a sequence by an arbitrary value.
((fn [ad se]
  (vec
   (butlast
    (interleave se
                (repeat (count se) ad)))))
 :z [:a :b :c])

;;41 Drop Every Nth Item
;;Write a function which drops every Nth item from a sequence.
((fn [se n]
   (loop [s (empty se) s1 se]
     (if (< (count s1) n)
       (into s s1)
       (recur (into s (butlast (take n s1))) (drop n s1)))))
 [1 2 3 4 5 6 7 8] 3)


;;42
;;Write a function which calculates factorials.
(#(reduce * (range 1 (+ % 1))) 5)


;;43 Reverse Interleave
;;Write a function which reverses the interleave process into x number of subsequences.
((fn re-inte [se n]
  (take n (lazy-seq
            (cons (take-nth n se)
                  (re-inte (drop 1 se) n)))))
 (range 9) 3)


;;44 Rotate Sequence
;;Write a function which can rotate a sequence in either direction.
((fn [idx se]
   (concat (drop (mod idx (count se)) se)
         (take (mod idx (count se)) se))) -2 [1 2 3 4 5])


;;46
;;Write a higher-order function which flips the order of the arguments of an input function.
(((fn [f]
     (fn [a b]
       (f b a))) take)  [1 2 3] 3)


;;49
;;Write a function which will split a sequence into two parts.
((fn [idx se]
  [(vec (take idx se)) (vec (drop idx se))]) 3 [1 2 3 4 5 6])


;;54 Partition a Sequence
;;Write a function which returns a sequence of lists of x items each. Lists of less than x items should not be returned.
((fn part [n se]
   (take (quot (count se) n)
         (lazy-seq
          (cons (take n se)
                (part n (drop n se))))))
 3 (range 8))


;;61 Map construction
;;Write a function which takes a vector of keys and a vector of values and constructs a map from them
((fn [s1 s2]
  (apply assoc {}
   (reverse
    (interleave s2 s1))))
 [:a :b :c] [1 2 3])


;;63 Re-implement Iterate
;;Given a side-effect free function f and an initial value x write a function which returns an infinite lazy sequence of x, (f x), (f (f x)), (f (f (f x))), etc.
(take 5 ((fn ite [f arg]
          (cons arg
                (lazy-seq (ite f (f arg)))))
         #(* 2 %) 1))

;;66
;;Given two integers, write a function which returns the greatest common divisor.
((fn [x y]
   (loop [ma (max x y) mi (min x y)]
     (if (zero? mi)
       ma
       (recur mi (rem ma mi))))) 1023 858)

;;67 Prime Numbers
;;Write a function which returns the first x number of prime numbers.
(#(take % (filter(fn [n]
  (cond (= n 0) false
        (= n 1) false
        (= n 2) true
        (even? n) false
        :else (loop [under (range 3 n)]
          (if (empty? under)
            true
            (if (zero? (rem n (first under)))
              false
              (recur (rest under))))))) (range))) 5)


;;81
;;Write a function which returns the intersection of two sets. The intersection is the sub-set of items that each set has in common.
((fn [set1 set2]
   (let [all (clojure.set/union set1 set2)
         diff1 (clojure.set/difference set1 set2)
         diff2 (clojure.set/difference set2 set1)
         diff (clojure.set/union diff1 diff2)]
     (clojure.set/difference all diff)))  #{0 1 2 3} #{2 3 4 5})


;;83 A Half-Truth
;;Write a function which takes a variable number of booleans. Your function should return true if some of the parameters are true, but not all of the parameters are true. Otherwise your function should return false.
((fn
   ([bl] false)
   ([bl & re]
   (if (= 2 (count (distinct (flatten [bl re]))))
     true
     false)))
   true )


;;88 Symmetric Difference
;;Write a function which returns the symmetric difference of two sets. The symmetric difference is the set of items belonging to one but not both of the two sets.
((fn [s1 s2]
   (clojure.set/union
    (clojure.set/difference s1 s2)
    (clojure.set/difference s2 s1))) #{1 2 3 4 5 6} #{1 3 5 7})


;;99 Product Digits
;;Write a function which multiplies two numbers and returns the result as a sequence of its digits.
((fn [x y]
   (loop [rs (* x y) ve []]
     (if (zero? rs)
       ve
       (recur (quot rs 10) (cons (rem rs 10) ve ))))) 99 9)


;;118 Re-implement Map
;;Map is one of the core elements of a functional programming language. Given a function f and an input sequence s, return a lazy sequence of (f x) for each element x in s.
((fn re-map [f se]
   (if-not (empty? se)
    (lazy-seq (cons (f (first se))
       (re-map f (rest se)))))) inc [1 2 3])


;;134
;;Write a function which, given a key and map, returns true iff the map contains an entry with that key and its value is nil.
((fn [x se]
   (cond (not (contains? se x)) false
         (nil? (se x)) true
         :else false)) :b {:a nil :b 2})


;;143 dot product
;;Create a function that computes the dot product of two sequences. You may assume that the vectors will have the same length.
(#(reduce + (map * %1 %2)) [1 2 3] [4 5 6])


;;156
;;When retrieving values from a map, you can specify default values in case the key is not found:
;;(= 2 (:foo {:bar 0, :baz 1} 2))
;;However, what if you want the map itself to contain the default values? Write a function which takes a default value and a sequence of keys and constructs a map.
((fn [ad se]
  (apply assoc {}
 (interleave (reverse se) (repeat ad)))) 0 [:a :b :c])

;;166 Comparisons
;;For any orderable data type it's possible to derive all of the basic comparison operations (<, ≤, =, ≠, ≥, and >) from a single operation (any operator but = or ≠ will work). Write a function that takes three arguments, a less than operator for the data and two items to compare. The function should return a keyword describing the relationship between the two items. The keywords for the relationship between x and y are as follows:
;;x = y → :eq
;;x > y → :gt
;;x < y → :lt
((fn [f a b]
  (if (f a b)
    :lt
    (if (= (f a b) (f b a))
      :eq
      :gt)))
 (fn [x y] (< (count x) (count y)))
 "peao"
 "plum")
