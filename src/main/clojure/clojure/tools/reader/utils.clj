(ns clojure.tools.reader.utils
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
   (symbol? f) {:tag f}
   (string? f) {:tag f}
   (keyword? f) {f true}
   :else f))
