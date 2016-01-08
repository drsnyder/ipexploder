(ns ipexploder.util
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn string->int [s]
  (Long/parseLong s))


(defn lazy-reader
  "Lazily read from fd."
  [fd]
  (lazy-seq
    (if-let [line (.readLine fd)]
      (cons line (lazy-reader fd))
      (.close fd))))

(defn read-file
  [file &{:keys [gunzip] :or {gunzip false}}]
  (lazy-reader (cond-> (io/input-stream file)
                         gunzip (java.util.zip.GZIPInputStream.)
                         true (io/reader))))

(defn process-csv [file]
    (with-open [rdr (io/reader file)]
          (doall (csv/read-csv rdr))))

(defn write-to-csv
  "Write the simulation data to the given file."
  [file data & opts]
  (let [writer (if opts (apply io/writer file opts) (io/writer file))]
  (with-open [out-file writer] 
    (csv/write-csv out-file data))))
