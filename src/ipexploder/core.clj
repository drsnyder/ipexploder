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

(defn explode-from-to [from-file to-file]
  (->> (process-csv from-file)
       (pmap explode-record)
       (mapcat identity)
       (write-to-csv to-file)))
