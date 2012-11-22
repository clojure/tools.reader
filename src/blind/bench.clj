(ns blind.bench
  (:require [blind.reader :as r] :reload-all
            ))

(def l (slurp "/home/bronsa/src/clojure/src/clj/clojure/core.clj"))

(defn a []
  (let [a (atom [])
        pl (r/string-push-back-reader l)]
    (loop [r (swap! a conj (r/read pl true nil true))]
      (if r (recur (try (swap! a conj (r/read pl true nil true))
                        (catch Exception _ )))))))

(defn b []
  (let [a (atom [])
        pl (java.io.PushbackReader. (java.io.StringReader. l))]
    (loop [r (swap! a conj (read pl true nil true))]
      (if r (recur (try (swap! a conj (read pl true nil true))
                        (catch Exception _ )))
          @a))))


;; (def c (first (drop-while (fn [[a b]] (= a b)) (map vector a b))))


