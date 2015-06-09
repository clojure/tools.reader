;;   Copyright (c) Nicola Mometto, Rich Hickey & contributors.
;;   The use and distribution terms for this software are covered by the
;;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;;   which can be found in the file epl-v10.html at the root of this distribution.
;;   By using this software in any fashion, you are agreeing to be bound by
;;   the terms of this license.
;;   You must not remove this notice, or any other, from this software.

(ns cljs.tools.reader.impl.utils
  (:require [cljs.util :as util]))

(def cljs-build-number
  (#?(:clj Integer. :cljs js/parseInt)
     (second (re-find #"\d\.\d-(\d+)" (util/clojurescript-version)))))

(defmacro compile-if-cljs<3255 [& body]
  (when (< cljs-build-number 3255)
    (cons 'do body)))

(defmacro compile-if-cljs<3291 [& body]
  (when (< cljs-build-number 3291)
    (cons 'do body)))

(defmacro compile-if-cljs<3308 [& body]
  (when (< cljs-build-number 3308)
    (cons 'do body)))
