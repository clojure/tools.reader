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
  (with-meta  '(^{:line 1 :column 2 :end-line 1 :end-column 5 :file "haiku.clj"} ns
                ^{:line 1 :column 5 :end-line 1 :end-column 32 :file "haiku.clj"} clojure.tools.reader.haiku)
    {:line 1 :column 1 :end-line 1 :end-column 32 :file "haiku.clj"}))

(def expected-haiku-defn
  (with-meta (list
              '^{:line 3 :column 2 :end-line 3 :end-column 7 :file "haiku.clj"} defn
              '^{:line 3 :column 7 :end-line 3 :end-column 13 :file "haiku.clj"} haiku
              "It will read the form\n    but will the form metadata be\n    or never become?"
              (with-meta ['^{:line 7 :column 6 :end-line 7 :end-column 17 :file "haiku.clj"} first-five
                          '^{:line 7 :column 17 :end-line 7 :end-column 30 :file "haiku.clj"} middle-seven
                          '^{:line 7 :column 30 :end-line 7 :end-column 40 :file "haiku.clj"} last-five]
                {:line 7 :column 5 :end-line 7 :end-column 40 :file "haiku.clj"})
              (with-meta (list '^{:line 8 :column 6 :end-line 8, :end-column 8 :file "haiku.clj"} -
                               (with-meta (list '^{:line 8 :column 9 :end-line 8 :end-column 15 :file "haiku.clj"} apply
                                                '^{:line 8 :column 15 :end-line 8 :end-column 17 :file "haiku.clj"} +
                                                ^{:last 'last-five :line 9 :column 34 :end-line 9 :end-column 41 :file "haiku.clj"}
                                                [1 2 3])
                                 {:line 8 :column 8 :end-line 9 :end-column 42 :file "haiku.clj"})
                               '^{:line 10 :column 8 :end-line 10 :end-column 19 :file "haiku.clj"} first-five
                               '^{:line 10 :column 19 :end-line 10 :end-column 32 :file "haiku.clj"} middle-seven)
                {:line 8 :column 5 :end-line 10 :end-column 32 :file "haiku.clj"}))
    {:line 3 :column 1 :end-line 10 :end-column 33 :file "haiku.clj"}))

(deftest read-metadata
  (let [reader (-> (test-reader)
                   (LineNumberingPushbackReader.)
                   (reader-types/indexing-push-back-reader 1 "haiku.clj"))
        first-form (read reader)
        second-form (read reader)]
    (is (= {:line 1 :column 1 :end-line 1 :end-column 32 :file "haiku.clj"} (meta first-form)))
    (let [comparisons (map vector (tree-seq coll? identity expected-haiku-ns)
                           (tree-seq coll? identity first-form))]
      (doseq [[expected actual] comparisons]
        (is (= [expected (meta expected)] [actual (meta actual)]))))
    (let [comparisons (map vector (tree-seq coll? identity expected-haiku-defn)
                           (tree-seq coll? identity second-form))]
      (doseq [[expected actual] comparisons]
        (is (= [expected (meta expected)] [actual (meta actual)]))))))

(def expected-haiku-ns-with-source
  (with-meta  '(^{:line 1 :column 2 :end-line 1 :end-column 5 :source "ns" :file "haiku.clj"} ns
                ^{:line 1 :column 5 :end-line 1 :end-column 32 :source "clojure.tools.reader.haiku" :file "haiku.clj"} clojure.tools.reader.haiku)
    {:line 1 :column 1 :end-line 1 :end-column 32 :source "(ns clojure.tools.reader.haiku)" :file "haiku.clj"}))

(def expected-haiku-defn-with-source
  (with-meta (list
              '^{:line 3 :column 2 :end-line 3 :end-column 7 :source "defn" :file "haiku.clj"} defn
              '^{:line 3 :column 7 :end-line 3 :end-column 13 :source "haiku" :file "haiku.clj"} haiku
              "It will read the form\n    but will the form metadata be\n    or never become?"
              (with-meta ['^{:line 7 :column 6 :end-line 7 :end-column 17 :source "first-five" :file "haiku.clj"} first-five
                          '^{:line 7 :column 17 :end-line 7 :end-column 30 :source "middle-seven" :file "haiku.clj"} middle-seven
                          '^{:line 7 :column 30 :end-line 7 :end-column 40 :source "last-five" :file "haiku.clj"} last-five]
                {:line 7 :column 5 :end-line 7 :end-column 40 :source "[first-five middle-seven last-five]" :file "haiku.clj"})
              (with-meta (list '^{:line 8 :column 6 :end-line 8, :end-column 8 :source "-" :file "haiku.clj"} -
                               (with-meta (list '^{:line 8 :column 9 :end-line 8 :end-column 15 :source "apply" :file "haiku.clj"} apply
                                                '^{:line 8 :column 15 :end-line 8 :end-column 17 :source "+" :file "haiku.clj"} +
                                                ^{:last 'last-five :line 9 :column 34 :end-line 9 :end-column 41 :source "^{:last last-five} [1 2 3]" :file "haiku.clj"}
                                                [1 2 3])
                                 {:line 8 :column 8 :end-line 9 :end-column 42 :source "(apply +
              ^{:last last-five} [1 2 3])" :file "haiku.clj"})
                               '^{:line 10 :column 8 :end-line 10 :end-column 19 :source "first-five" :file "haiku.clj"} first-five
                               '^{:line 10 :column 19 :end-line 10 :end-column 32 :source "middle-seven" :file "haiku.clj"} middle-seven)
                {:line 8 :column 5 :end-line 10 :end-column 32 :source "(- (apply +
              ^{:last last-five} [1 2 3])
       first-five middle-seven)" :file "haiku.clj"}))
    {:line 3 :column 1 :end-line 10 :end-column 33 :source "(defn haiku
    \"It will read the form
    but will the form metadata be
    or never become?\"
    [first-five middle-seven last-five]
    (- (apply +
              ^{:last last-five} [1 2 3])
       first-five middle-seven))" :file "haiku.clj"}))

(deftest read-metadata-with-source
  (let [reader (-> (test-reader)
                   (LineNumberingPushbackReader.)
                   (reader-types/source-logging-push-back-reader 1 "haiku.clj"))
        first-form (read reader)
        second-form (read reader)]
    (is (= {:line 1 :column 1 :end-line 1 :end-column 32 :source "(ns clojure.tools.reader.haiku)" :file "haiku.clj"} (meta first-form)))
    (let [comparisons (map vector (tree-seq coll? identity expected-haiku-ns-with-source)
                           (tree-seq coll? identity first-form))]
      (doseq [[expected actual] comparisons]
        (is (= [expected (meta expected)] [actual (meta actual)]))))
    (let [comparisons (map vector (tree-seq coll? identity expected-haiku-defn-with-source)
                           (tree-seq coll? identity second-form))]
      (doseq [[expected actual] comparisons]
        (is (= [expected (meta expected)] [actual (meta actual)]))))))
