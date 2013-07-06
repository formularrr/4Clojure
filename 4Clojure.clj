(ns solutions)

;;#21.Nth Element
;;Write a function which returns the Nth element from a sequence.
((fn [se n]
   (first (drop n se))) '(4 5 6 7) 2)



;;#22 Count a Sequence
;;Write a function which returns the total number of elements in a sequence.
;;first
((fn count-seq [coll]
   (if (empty? (seq coll))
       0
       (inc (count-seq (rest (seq coll)))))) 
 "Hello World")


;;23
;;Write a function which reverses a sequence.
((fn reverse-seq [n]
   (if-not (empty? n)
    (lazy-seq
     (cons (last n)
          (reverse-seq (butlast n)))))) [1 2 3 4 5])


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
((fn [coll]
   (map #(first %) 
        (partition-by identity (seq coll))))  
 [1 1 2 3 3 2 2 3])


;;31 Pack a sequence
;;Write a function which packs consecutive duplicates into sub-lists.
(partition-by identity [1 1 2 1 1 1 ])


;;32 Duplicate a SequenceSolutions
;;Write a function which duplicates each element of a sequence.
(#(interleave % %) [[1 2] [3 4]])


;;33
;;Write a function which freplicates each element of a sequence a variable number of times.
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
((fn [coll idx]
   (flatten
    (map #(take (dec idx) %) 
         (partition-all 3 idx coll))))
 [1 2 3 4 5 ] 3)


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


;;50 Split by Type
;;Write a function which takes a sequence consisting of items with different types and splits them up into a set of homogeneous sub-sequences. The internal order of each sub-sequence should be maintained, but the sub-sequences themselves can be returned in any order (this is why 'set' is used in the test cases).
((fn [coll]
   (map #(second %)
        (group-by class coll)))
 [:a "foo" "bar" :b])


;;54 Partition a Sequence
;;Write a function which returns a sequence of lists of x items each. Lists of less than x items should not be returned.
((fn part [n se]
   (take (quot (count se) n)
         (lazy-seq
          (cons (take n se)
                (part n (drop n se))))))
 3 (range 8))


;;55 Count Occurrences
;;Write a function which returns a map containing the number of occurences of each distinct item in a sequence.
((fn [coll]
   (let [rs (reverse (group-by identity  coll))]
     (zipmap
       (map #(first %) rs)
        (map #(count (second %)) rs)))) [1 1 2 3 2 1 1])


;;56 Find Distinct Items
;;Write a function which removes the duplicates from a sequence. Order of the items must be maintained.
(#(let [s (map first (group-by identity %))]
    (if (= (count s) (count %)) % s))
  [1 2 1 3 1 2 4])


;;59 Juxtaposition
;;Take a set of functions and return a new function that takes a variable number of arguments and returns a sequence containing the result of applying each function left-to-right to the argument list.
(((fn [& f-more]
   (fn [& coll-more]
     (loop [f f-more rs []]
       (if (empty? f)
         rs
         (recur (rest f)
                (conj rs 
                      (apply (first f) coll-more)))))))
  + max min) 
 2 3 5 1 6 4)


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


;;90 Cartesian Product
;;Write a function which calculates the Cartesian product of two sets.
((fn [coll1 coll2]
   (into #{}
         ((fn [coll1 coll2]
           (loop [coll coll2 rs []]
             (if (empty? coll)
               rs
               (recur (rest coll)
                      (concat rs
                             (partition 2
                                        (interleave coll1
                                                    (repeat (first coll)))))))))
          coll1 coll2)))
 #{"ace" "king" "queen"}  #{"♠" "♥" "♦" "♣"})


;;95 To Tree, or not to Tree
;;Write a predicate which checks whether or not a given sequence represents a binary tree. Each node in the tree must have a value, a left child, and a right child.
((fn [coll]
   (let [tr (vec (flatten coll))]
     (cond (even? (count tr)) false
           (not (= (count 
                    (filter #(if (false? %) false true) tr)) 
                   (count tr))) false
     :else true))) 
'(:a (:b nil nil) nil))


;;97 Pascal's Triangle
;;Pascal's triangle is a triangle of numbers computed using the following rules:
;;The first row is 1.
;;Each successive row is computed by adding together adjacent numbers in the row above, and adding a 1 to the beginning and end of the row.
;;Write a function which returns the nth row of Pascal's Triangle. 
((fn pascal [n]
     (cond (= n 1) [1]
           (= n 2) [1 1]
           :else (flatten (list 1
                                (map #(+ (first %) (second %)) 
                                     (partition 2 1 (pascal (dec n))))
                                1))))
 6)



;;99 Product Digits
;;Write a function which multiplies two numbers and returns the result as a sequence of its digits.
((fn [x y]
   (loop [rs (* x y) ve []]
     (if (zero? rs)
       ve
       (recur (quot rs 10) (cons (rem rs 10) ve ))))) 99 9)


;;102 intoCamelCase
;;When working with java, you often need to create an object with fieldsLikeThis, but you'd rather work with a hashmap that has :keys-like-this until it's time to convert. Write a function which takes lower-case hyphen-separated strings and converts them to camel-case strings.
((fn [coll]
   (let [s (clojure.string/split coll #"\-")]
     (apply str
            (first s)
            (map #(clojure.string/capitalize %)
                 (drop 1 s)))))
 "multi-word-key")

;;107 Simple closures
;;Lexical scope and first-class functions are two of the most basic building blocks of a functional language like Clojure. When you combine the two together, you get something very powerful called lexical closures. With these, you can exercise a great deal of control over the lifetime of your local bindings, saving their values for use later, long after the code you're running now has finished.
;;It can be hard to follow in the abstract, so let's build a simple closure. Given a positive integer n, return a function (f x) which computes xn. Observe that the effect of this is to preserve the value of n for use outside the scope in which it is defined.
(((fn [base]
   (fn [exp]
     (int (Math/pow exp base)))) 2) 16)


;;118 Re-implement Map
;;Map is one of the core elements of a functional programming language. Given a function f and an input sequence s, return a lazy sequence of (f x) for each element x in s.
((fn re-map [f se]
   (if-not (empty? se)
    (lazy-seq (cons (f (first se))
       (re-map f (rest se)))))) inc [1 2 3])


;;122 Read a binary number
;;Convert a binary number, provided in the form of a string, to its numerical value.
((fn [coll]
  (let [v (vec (reverse coll))]
    (int (loop [cnt (dec (count v)) rs 0]
          (if (neg? cnt)
            rs
            (if (= \0 (v cnt))
              (recur (dec cnt) rs)
              (recur (dec cnt) 
                     (+ rs (Math/pow 2 cnt)))))))))
  "10101010101")


;;134
;;Write a function which, given a key and map, returns true iff the map contains an entry with that key and its value is nil.
((fn [x se]
   (cond (not (contains? se x)) false
         (nil? (se x)) true
         :else false)) :b {:a nil :b 2})


;;135 Infix Calculator
;;Your friend Joe is always whining about Lisps using the prefix notation for math. Show him how you could easily write a function that does math using the infix notation. Is your favorite language that flexible, Joe? Write a function that accepts a variable length mathematical expression consisting of numbers and the operations +, -, *, and /. Assume a simple calculator that does not do precedence and instead just calculates left to right.
((fn [op1 & more]
   (loop [op1 op1 more more]
       (if (empty? more)
         op1
        (recur ((first more) op1 (second more))
               (drop 2 more)))))
 2 + 8 - 9 / 2)


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


;;157 Indexing Sequences
;;Transform a sequence into a sequence of pairs containing the original elements along with their index.
(#(partition 2 (interleave % (range (count %)))) [[:foo] {:bar :baz}])


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
