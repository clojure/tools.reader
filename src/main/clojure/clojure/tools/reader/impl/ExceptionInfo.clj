(ns ^:skip-wiki clojure.tools.reader.impl.ExceptionInfo
  (:gen-class :extends java.lang.Exception
              :init init
              :constructors {[String clojure.lang.IPersistentMap] [String]
                             [String clojure.lang.IPersistentMap Throwable] [String Throwable]}
              :state data
              :methods [[getData [] clojure.lang.IPersistentMap]]))

(defn -init
  ([s data]
     [[s] data])
  ([s data throwable]
     [[s throwable] data]))

(defn -getData [^clojure.tools.reader.impl.ExceptionInfo this]
  (.data this))

(defn -toString [^clojure.tools.reader.impl.ExceptionInfo this]
  (str "clojure.tools.reader.ExceptionInfo: " (.getMessage this) " " (str (.data this))))
