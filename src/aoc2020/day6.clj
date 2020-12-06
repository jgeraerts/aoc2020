(ns aoc2020.day6
  (:require [clojure.java.io :as io]
            [clojure.set :as sets]))

(defn input-seq [input]
  (-> (io/resource input)
      (io/reader)
      (line-seq)))

(defn reduce-into-groups [[previous-groups current-group] answer]
  (if (or (nil? answer) (= answer ""))
    [(conj previous-groups current-group) []]
    [previous-groups (conj current-group (into #{} (seq answer)))]))

(defn group-input [input]
  (->> (reduce reduce-into-groups [[] []] input)
      (apply conj)))

(defn day6 [input set-fn]
  (->> (input-seq input)
      (group-input)
      (map (comp count #(apply set-fn %)))
      (reduce +)))

(defn day6a [input]
  (day6 input sets/union))

(defn day6b [input]
  (day6 input sets/intersection))

(comment
  (day6a "day6.txt")
  (day6b "day6.txt")
  )
