(ns ipexploder.core
  (:require [ipexploder.util :refer :all]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

;0 id    bigint    
;1 ip_from    bigint    
;2 ip_to    bigint    
;3 country_code    string    
;4 region    string    
;5 city    string    
;postal_code    string    
;latitude    string    
;longitude    string    
;dma_code    int    
;area_code    int

(defn from-field [r]
  (string->int (nth r 1)))

(defn to-field [r]
  (string->int (nth r 2)))

(defn record-range [r]
  (range (from-field r) (+ (to-field r) 1)))

(defn record-width [r]
  (+ (- (to-field r)
        (from-field r)) 1))

(defn explode-record [r]
  ;  id,ip (that is the ip_number which we are exploding) ,
  ; country_code,region,city
  (for [ip (record-range r)]
    (into [(nth r 0) ip] (subvec r 3 6))))

(defn explode-all [l]
  (pmap explode-record l))

(defn exploaded->stream [ll]
  (lazy-seq
    (if (seq ll)
      (into (vec (first ll)) (exploaded->stream (rest ll))))))

(defn explode-from-to [from-file to-file]
  (let [in-data (process-csv from-file)
        exploaded (explode-all in-data)
        stream (mapcat identity exploaded)]
    (write-to-csv to-file stream)))

(defn expload-from-seq-to-file [s to-file]
  (let [exploaded (explode-all s)
        stream (exploaded->stream exploaded)]
    (write-to-csv to-file stream)))
