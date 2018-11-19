(ns com.yuranos.general.loops)

;(with-open [r (clojure.java.io/input-stream "myfile.txt")]
;  (loop [c (.read r)]
;    (if (not= c -1)
;      (do
;        (print (char c))
;        (recur (.read r))))))

(loop [i 0]
  (when (< i 5)
    (println i)
    (recur (inc i)); loop i will take this value
    ))

(loop [i 0]
  (when (< i 5)
    (println i)
    (recur (inc i)); loop i will take this value
    ))

; A loop that sums the numbers 10 + 9 + 8 + ...
; Set initial values count (cnt) from 10 and down
(defn sumTries [] (loop [sum 0 cnt 10]
  ; If count reaches 0 then exit the loop and return sum
  (if (= cnt 0)
    sum
    ; Otherwise add count to sum, decrease count and
    ; use recur to feed the new values back into the loop
    (recur (+ cnt sum) (dec cnt)))))

(defn find-needle [needle haystack]
  ;loop binds initial values once,
  ;then binds values from each recursion call
  (loop [needle needle
         maybe-here haystack
         not-here '()]

    (let [needle? (first maybe-here)]

      ;test for return or recur
      (if (or (= (str needle?) (str needle))
              (empty? maybe-here))

        ;return results
        [needle? maybe-here not-here]

        ;recur calls loop with new values
        (recur needle
          (rest maybe-here)
          (concat not-here (list (first maybe-here))))))))

;user=>(find-needle "|" "hay|stack")
;[\| (\| \s \t \a \c \k) (\h \a \y)]


;; Here are the definitions.
(defn mymax [x y]
  (min x y))

(defn find-max [x y]
  (max x y))

;user=> (let [max mymax]
;         (find-max 10 20))

;20 ;let is ineffective outside current lexical scope


;user=> (binding [max mymax]
;         (find-max 10 20))
;10 ;because max is now acting as min


(defn -main
  [& args]
  (prn (loop [sum 0
              cnt 10]
         ; If count reaches 0 then exit the loop and return sum
         (if (= cnt 0)
           sum
           ; Otherwise add count to sum, decrease count and
           ; use recur to feed the new values back into the loop
           (recur (+ cnt sum) (dec cnt))))))
