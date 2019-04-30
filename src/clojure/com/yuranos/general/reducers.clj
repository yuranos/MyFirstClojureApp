(ns com.yuranos.general.reducers)

(require '[clojure.core.reducers :as r])

(def s (range 0 9999999))
(time (reduce + 0 (map inc (filter even? s))))
;"Elapsed time: 631.120905 msecs"
(time (reduce + 0 (r/map inc (r/filter even? s))))
;"Elapsed time: 327.997703 msecs"
;But this is even faster (notice we had to convert s to s vector)
(def sv (vec s))

;The trick is breaking the input sequence into chunks,
; reducing each chunk and then reducing the results of the chunks.
;In general most users will not call r/reduce directly and instead should prefer r/fold,
;which implements parallel reduce and combine.
(time (r/fold + (r/map inc (r/filter even? sv))))
;"Elapsed time: 87.75896 msecs"

(reduce ((comp (filter even?) (map inc)) +) 0 sv)

