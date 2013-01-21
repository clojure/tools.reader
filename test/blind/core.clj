(ns blind.core
  (:refer-clojure :exclude [read-string])
  (:require [blind.reader :refer [read-string]]
            [clojure.test :refer [deftest is]]))

(deftest read-integer
  (is (== 42 (read-string "42")))
  (is (== +42 (read-string "+42")))
  (is (== -42 (read-string "-42")))

  (is (== 42 (read-string "42N")))
  (is (== +42 (read-string "+42N")))
  (is (== -42 (read-string "-42N")))

  (is (== 0 (read-string "0")))
  (is (== 0N (read-string "0N")))

  (is (== 042 (read-string "042")))
  (is (== +042 (read-string "+042")))
  (is (== -042 (read-string "-042")))

  (is (== 0x42e (read-string "0x42e")))
  (is (== +0x42e (read-string "+0x42e")))
  (is (== -0x42e (read-string "-0x42e"))))

(deftest read-floating
  (is (== 42.23 (read-string "42.23")))
  (is (== +42.23 (read-string "+42.23")))
  (is (== -42.23 (read-string "-42.23")))

  (is (== 42.23M (read-string "42.23M")))
  (is (== +42.23M (read-string "+42.23M")))
  (is (== -42.23M (read-string "-42.23M")))

  (is (== 42.2e3 (read-string "42.2e3")))
  (is (== +42.2e+3 (read-string "+42.2e+3")))
  (is (== -42.2e-3 (read-string "-42.2e-3")))

  (is (== 42.2e3M (read-string "42.2e3M")))
  (is (== +42.2e+3M (read-string "+42.2e+3M")))
  (is (== -42.2e-3M (read-string "-42.2e-3M"))))

(deftest read-ratio
  (is (== 4/2 (read-string "4/2")))
  (is (== +4/2 (read-string "+4/2")))
  (is (== -4/2 (read-string "-4/2"))))

(deftest read-keyword
  (is (= :foo-bar (read-string ":foo-bar")))
  (is (= :foo/bar (read-string ":foo/bar")))
  (is (= :user/foo-bar (read-string "::foo-bar")))
  (is (= :clojure.core/foo-bar
         (do (alias 'core 'clojure.core)
             (read-string "::core/foo-bar")))))

(deftest read-symbol
  (is (= 'foo (read-string "foo")))
  (is (= 'foo/bar (read-string "foo/bar")))
  (is (= "foo//" (str (read-string "foo//")))) ;; the clojure reader can't read this
  (is (= (str 'NaN) (str (read-string "NaN")))) ;; the clojure reader can't read this
  (is (= Double/POSITIVE_INFINITY (read-string "Infinity"))) ;; the clojure reader can't read this
  (is (= Double/POSITIVE_INFINITY (read-string "+Infinity"))) ;; the clojure reader can't read this
  (is (= Double/NEGATIVE_INFINITY (read-string "-Infinity")))) ;; the clojure reader can't read this

(deftest read-char
  (is (= \f (read-string "\\f")))
  (is (= \u0194 (read-string "\\u0194")))
  (is (= \a (read-string "\\x61"))) ;; the clojure reader can't read this
  (is (= \o123 (read-string "\\o123")))
  (is (= \newline (read-string "\\newline"))))

(deftest read-string*
  (is (= "foo bar" (read-string "\"foo bar\"")))
  (is (= "foo\\bar" (read-string "\"foo\\\\bar\"")))
  (is (= "foo\000bar" (read-string "\"foo\\000bar\"")))
  (is (= "foo\u0194bar" (read-string "\"foo\\u0194bar\"")))
  (is (= "fooabar" (read-string "\"foo\\x61bar\""))) ;; the clojure reader can't read this
  (is (= "foo\123bar" (read-string "\"foo\\123bar\""))))

(deftest read-regex
  (is (= (str #"\[\]?(\")\\")
         (str (read-string "#\"\\[\\]?(\\\")\\\\\"")))))

(deftest read-list
  (is (= '() (read-string "()")))
  (is (= '(foo bar) (read-string "(foo bar)")))
  (is (= '(foo (bar) baz) (read-string "(foo (bar) baz)"))))

(deftest read-vector
  (is (= '[] (read-string "[]")))
  (is (= '[foo bar] (read-string "[foo bar]")))
  (is (= '[foo [bar] baz] (read-string "[foo [bar] baz]"))))

(deftest read-map
  (is (= '{} (read-string "{}")))
  (is (= '{foo bar} (read-string "{foo bar}")))
  (is (= '{foo {bar baz}} (read-string "{foo {bar baz}}"))))

(deftest read-set
  (is (= '#{} (read-string "#{}")))
  (is (= '#{foo bar} (read-string "#{foo bar}")))
  (is (= '#{foo #{bar} baz} (read-string "#{foo #{bar} baz}"))))

(deftest read-metadata
  (is (= {:foo true} (meta (read-string "^:foo 'bar"))))
  (is (= {:foo 'bar} (meta (read-string "^{:foo bar} 'baz"))))
  (is (= {:tag "foo"} (meta (read-string "^\"foo\" 'bar"))))
  (is (= {:tag 'String} (meta (read-string "^String 'x")))))

(deftest read-quote
  (is (= ''foo (read-string "'foo"))))

(deftest read-syntax-quote
  (is (= '`user/foo (read-string "`foo")))
  (is (= '`+ (read-string "`+")))
  (is (= '`foo/bar (read-string "`foo/bar")))
  (is (= '`1 (read-string "`1")))
  (is (= '`(1 (~2 ~@(3))) (read-string "`(1 (~2 ~@(3)))"))))

(deftest read-deref
  (is (= '@foo (read-string "@foo"))))

(deftest read-var
  (is (= '(var foo) (read-string "#'foo"))))

(deftest read-fn
  (is (= '(fn* [] (foo bar baz)) (read-string "#(foo bar baz)"))))

(deftest read-arg
  (is (= 14 ((eval (read-string "#(apply + % %1 %3 %&)")) 1 2 3 4 5))))

(deftest read-eval
  (is (= 3 (read-string "#=(+ 1 2)"))))
