(ns com.yuranos.general.seq-namespace)

(def myseq (seq [1 2 4 3]))

(group-by count ["a" "as" "asd" "aa" "asdf" "qwer"])

(some odd? [1 2 3 4])
;true

(flatten [1 2 3 [4 5 6]])
;(1 2 3 4 5 6)

;First from each, then second from each, etc.
(interleave [10 11 12] [20 21 22] [30 31])
;(10 20 30 11 21 31)

;Using a set as a predicate
(keep #{0 1 2 3} #{2 3 4 5})

;Infinitely repeating 1 2 1 2 1 2.....
(cycle [1 2])
