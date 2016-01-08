(ns user
  (:require [clojure.pprint :refer [pprint]]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [ipexploder.core :refer :all]
            [ipexploder.util :refer :all]))

(def d (csv/read-csv (io/reader "data/sample.csv")))
(def all (csv/read-csv (io/reader "data/lookup_ip_country.csv")))
;(def all (process-csv "data/lookup_ip_country.csv"))




