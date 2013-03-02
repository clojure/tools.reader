(ns ^:skip-wiki clojure.tools.reader.impl.utils
  (:require clojure.tools.reader.impl.ExceptionInfo) ;; force loading
  (:refer-clojure :exclude [char]))

(defn char [x]
  (when x
    (clojure.core/char x)))

;; getColumnNumber and *default-data-reader-fn* are available only since clojure-1.5.0-beta1
(def >=clojure-1-5-alpha*?
  (let [{:keys [minor qualifier]} *clojure-version*]
    (and (>= minor 5)
         (not= "alpha"
               (when qualifier
                 (subs qualifier 0 (dec (count qualifier))))))))

(defmacro ^:private compile-if [cond then else]
  (if (eval cond)
    then
    else))

(compile-if (= 3 (:minor *clojure-version*))
  (do
    (defn ex-info
      ([msg map]
         (clojure.tools.reader.impl.ExceptionInfo. msg map))
      ([msg map cause]
         (clojure.tools.reader.impl.ExceptionInfo. msg map cause)))
    (defn ex-data
      [ex]
      (.getData ex))
    (defn ex-info? [ex]
      (instance? clojure.tools.reader.impl.ExceptionInfo ex)))

  (defn ex-info? [ex]
    (instance? clojure.lang.ExceptionInfo ex)))

(defn whitespace?
  "Checks whether a given character is whitespace"
  [ch]
  (when ch
    (or (Character/isWhitespace ^Character ch)
        (identical? \,  ch))))

(defn numeric?
  "Checks whether a given character is numeric"
  [^Character ch]
  (when ch
    (Character/isDigit ch)))

(defn comment-prefix?
  "Checks whether the character begins a comment."
  [ch]
  (identical? \;  ch))

(defn newline? [c]
  "Checks whether the character is a newline"
  (or (identical? \newline c)
      (nil? c)))

(defn desugar-meta
  [f]
  (cond
    (keyword? f) {f true}
    (symbol? f)  {:tag f}
    (string? f)  {:tag f}
    :else        f))
