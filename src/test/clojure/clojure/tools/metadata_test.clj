(ns clojure.tools.metadata-test
  (:refer-clojure :exclude [read *default-data-reader-fn*])
  (:use [clojure.tools.reader :only [read *default-data-reader-fn*]]
        [clojure.test :only [deftest is]])
  (:require [clojure.tools.reader.reader-types :as reader-types]
            [clojure.walk :as walk])
  (:import java.nio.charset.Charset
           (java.io StringReader)
           clojure.lang.LineNumberingPushbackReader))

(def test-contents
  "Contents of a file stream for testing."
  "(ns clojure.tools.reader.haiku)\n\n(defn haiku
    \"It will read the form
    but will the form metadata be
    or never become?\"
    [first-five middle-seven last-five]
    (- (apply +
              ^{:last last-five} [1 2 3])
       first-five middle-seven))")

(defn test-reader
  "Return a fresh byte array input stream reading off test-bytes"
  []
  (StringReader. test-contents))

(def expected-haiku-ns
  (with-meta  '(^{:line 1 :column 2 :end-line 1 :end-column 4} ns
                ^{:line 1 :column 5 :end-line 1 :end-column 31} clojure.tools.reader.haiku)
    {:line 1 :column 1 :end-line 1 :end-column 32}))

(def expected-haiku-defn
  (with-meta (list
              '^{:line 3 :column 2 :end-line 3 :end-column 6} defn
              '^{:line 3 :column 7 :end-line 3 :end-column 12} haiku
              "It will read the form\n    but will the form metadata be\n    or never become?"
              (with-meta ['^{:line 7 :column 6 :end-line 7 :end-column 16} first-five
                          '^{:line 7 :column 17 :end-line 7 :end-column 29} middle-seven
                          '^{:line 7 :column 30 :end-line 7 :end-column 39} last-five]
                {:line 7 :column 5 :end-line 7 :end-column 40})
              (with-meta (list '^{:line 8 :column 6 :end-line 8, :end-column 7} -
                               (with-meta (list '^{:line 8 :column 9 :end-line 8 :end-column 14} apply
                                                '^{:line 8 :column 15 :end-line 8 :end-column 16} +
                                                ^{:last 'last-five :line 9 :column 34 :end-line 9 :end-column 41}
                                                [1 2 3])
                                 {:line 8 :column 8 :end-line 9 :end-column 42})
                               '^{:line 10 :column 8 :end-line 10 :end-column 18} first-five
                               '^{:line 10 :column 19 :end-line 10 :end-column 31} middle-seven)
                {:line 8 :column 5 :end-line 10 :end-column 32}))
    {:line 3 :column 1 :end-line 10 :end-column 33}))

(deftest read-metadata
  (let [reader (-> (test-reader)
                   (LineNumberingPushbackReader.)
                   (reader-types/indexing-push-back-reader 1 "haiku.clj"))
        first-form (read reader)
        second-form (read reader)]
    (is (= {:line 1 :column 1 :end-line 1 :end-column 32} (meta first-form)))
    (let [comparisons (map vector (tree-seq coll? identity expected-haiku-ns)
                           (tree-seq coll? identity first-form))]
      (doseq [[expected actual] comparisons]
        (is (= [expected (meta expected)] [actual (meta actual)]))))
    (let [comparisons (map vector (tree-seq coll? identity expected-haiku-defn)
                           (tree-seq coll? identity second-form))]
      (doseq [[expected actual] comparisons]
        (is (= [expected (meta expected)] [actual (meta actual)]))))))

(def expected-haiku-ns-with-source
  (with-meta  '(^{:line 1 :column 2 :end-line 1 :end-column 4 :source "ns"} ns
                ^{:line 1 :column 5 :end-line 1 :end-column 31 :source "clojure.tools.reader.haiku"} clojure.tools.reader.haiku)
    {:line 1 :column 1 :end-line 1 :end-column 32 :source "(ns clojure.tools.reader.haiku)"}))

(def expected-haiku-defn-with-source
  (with-meta (list
              '^{:line 3 :column 2 :end-line 3 :end-column 6 :source "defn"} defn
              '^{:line 3 :column 7 :end-line 3 :end-column 12 :source "haiku"} haiku
              "It will read the form\n    but will the form metadata be\n    or never become?"
              (with-meta ['^{:line 7 :column 6 :end-line 7 :end-column 16 :source "first-five"} first-five
                          '^{:line 7 :column 17 :end-line 7 :end-column 29 :source "middle-seven"} middle-seven
                          '^{:line 7 :column 30 :end-line 7 :end-column 39 :source "last-five"} last-five]
                {:line 7 :column 5 :end-line 7 :end-column 40 :source "[first-five middle-seven last-five]"})
              (with-meta (list '^{:line 8 :column 6 :end-line 8, :end-column 7 :source "-"} -
                               (with-meta (list '^{:line 8 :column 9 :end-line 8 :end-column 14 :source "apply"} apply
                                                '^{:line 8 :column 15 :end-line 8 :end-column 16 :source "+"} +
                                                ^{:last 'last-five :line 9 :column 34 :end-line 9 :end-column 41 :source "^{:last last-five} [1 2 3]"}
                                                [1 2 3])
                                 {:line 8 :column 8 :end-line 9 :end-column 42 :source "(apply +
              ^{:last last-five} [1 2 3])"})
                               '^{:line 10 :column 8 :end-line 10 :end-column 18 :source "first-five"} first-five
                               '^{:line 10 :column 19 :end-line 10 :end-column 31 :source "middle-seven"} middle-seven)
                {:line 8 :column 5 :end-line 10 :end-column 32 :source "(- (apply +
              ^{:last last-five} [1 2 3])
       first-five middle-seven)"}))
    {:line 3 :column 1 :end-line 10 :end-column 33 :source "(defn haiku
    \"It will read the form
    but will the form metadata be
    or never become?\"
    [first-five middle-seven last-five]
    (- (apply +
              ^{:last last-five} [1 2 3])
       first-five middle-seven))"}))

(deftest read-metadata-with-source
  (let [reader (-> (test-reader)
                   (LineNumberingPushbackReader.)
                   (reader-types/source-logging-push-back-reader 1 "haiku.clj"))
        first-form (read reader)
        second-form (read reader)]
    (is (= {:line 1 :column 1 :end-line 1 :end-column 32 :source "(ns clojure.tools.reader.haiku)"} (meta first-form)))
    (let [comparisons (map vector (tree-seq coll? identity expected-haiku-ns-with-source)
                           (tree-seq coll? identity first-form))]
      (doseq [[expected actual] comparisons]
        (is (= [expected (meta expected)] [actual (meta actual)]))))
    (let [comparisons (map vector (tree-seq coll? identity expected-haiku-defn-with-source)
                           (tree-seq coll? identity second-form))]
      (doseq [[expected actual] comparisons]
        (is (= [expected (meta expected)] [actual (meta actual)]))))))
