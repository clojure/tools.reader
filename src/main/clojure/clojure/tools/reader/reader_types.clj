(ns clojure.tools.reader.reader-types
  (:refer-clojure :exclude [char read-line])
  (:require [clojure.tools.reader.impl.utils :refer :all])
  (:import clojure.lang.LineNumberingPushbackReader
           (java.io InputStream BufferedReader)))

(defmacro ^:private update! [what f]
  (list 'set! what (list f what)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; reader protocols
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defprotocol Reader
  (read-char [reader] "Returns the next char from the Reader, nil if the end of stream has been reached")
  (peek-char [reader] "Returns the next char from the Reader without removing it from the reader stream"))

(defprotocol IPushbackReader
  (unread [reader ch] "Push back a single character on to the stream"))

(defprotocol IndexingReader
  (get-line-number [reader])
  (get-column-number [reader]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; reader deftypes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftype StringReader
    [^String s s-len ^:unsynchronized-mutable s-pos]
  Reader
  (read-char [reader]
    (when (> s-len s-pos)
      (let [r (nth s s-pos)]
        (update! s-pos inc)
        r)))
  (peek-char [reader]
    (when (> s-len s-pos)
      (nth s s-pos))))

(deftype InputStreamReader [^InputStream is ^:unsynchronized-mutable ^bytes buf]
  Reader
  (read-char [reader]
    (if buf
      (let [c (aget buf 0)]
        (set! buf nil)
        (char c))
      (let [c (.read is)]
        (when (>= 0 c)
          (char c)))))
  (peek-char [reader]
    (when-not buf
      (set! buf (byte-array 1))
      (when (== -1 (.read is buf))
        (set! buf nil)))
    (when buf
      (char (aget buf 0)))))

(deftype PushbackReader
    [rdr ^objects buf buf-len ^:unsynchronized-mutable buf-pos]
  Reader
  (read-char [reader]
    (char
     (if (< buf-pos buf-len)
       (let [r (aget buf buf-pos)]
         (update! buf-pos inc)
         r)
       (read-char rdr))))
  (peek-char [reader]
    (char
     (if (< buf-pos buf-len)
       (aget buf buf-pos)
       (peek-char rdr))))
  IPushbackReader
  (unread [reader ch]
    (when ch
      (if (zero? buf-pos) (throw (RuntimeException. "Pushback buffer is full")))
      (update! buf-pos dec)
      (aset buf buf-pos ch))))

(defn- normalize-newline [rdr ch]
  (if (identical? \return ch)
    (let [c (peek-char rdr)]
      (when (identical? \formfeed c)
        (read-char rdr))
      \newline)
    ch))

(deftype IndexingPushbackReader
    [rdr ^:unsynchronized-mutable line ^:unsynchronized-mutable column
     ^:unsynchronized-mutable line-start? ^:unsynchronized-mutable prev]
  Reader
  (read-char [reader]
    (when-let [ch (read-char rdr)]
      (let [ch (normalize-newline rdr ch)]
        (set! prev line-start?)
        (set! line-start? (newline? ch))
        (when line-start?
          (set! column 0)
          (update! line inc))
        (update! column inc)
        ch)))

  (peek-char [reader]
    (peek-char rdr))

  IPushbackReader
  (unread [reader ch]
    (when line-start? (update! line dec))
    (set! line-start? prev)
    (update! column dec)
    (unread rdr ch))

  IndexingReader
  (get-line-number [reader] (int (inc line)))
  (get-column-number [reader] (int column)))

(extend-type java.io.PushbackReader
  Reader
  (read-char [rdr]
    (let [c (.read ^java.io.PushbackReader rdr)]
      (when (>= 0 c)
        (normalize-newline rdr (char c)))))

  (peek-char [rdr]
    (when-let [c (read-char rdr)]
      (unread rdr c)
      c))

  IPushbackReader
  (unread [rdr c]
    (when c
      (.unread ^java.io.PushbackReader rdr (int c)))))

(extend LineNumberingPushbackReader
  IndexingReader
  {:get-line-number (fn [rdr] (.getLineNumber ^LineNumberingPushbackReader rdr))
   :get-column-number (if >=clojure-1-5-alpha*?
                        (fn [rdr]
                          (.getColumnNumber ^LineNumberingPushbackReader rdr))
                        (fn [rdr] 0))})

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; fast check for provided implementations
(defn indexing-reader? [rdr]
  "Returns true if the reader satisfies IndexingReader"
  (or (instance? clojure.tools.reader.reader_types.IndexingReader rdr)
      (instance? LineNumberingPushbackReader rdr)
      (and (not (instance? clojure.tools.reader.reader_types.StringReader rdr))
           (not (instance? clojure.tools.reader.reader_types.InputStreamReader rdr))
           (get (:impls IndexingReader) (class rdr)))))

(defn string-reader
  "Creates a StringReader from a given string"
  ([^String s]
     (StringReader. s (count s) 0)))

(defn string-push-back-reader
  "Creates a PushbackReader from a given string"
  ([s]
     (string-push-back-reader s 1))
  ([^String s buf-len]
     (PushbackReader. (string-reader s) (object-array buf-len) buf-len buf-len)))

(defn input-stream-reader
  "Creates an InputStreamReader from an InputString"
  [is]
  (InputStreamReader. is nil))

(defn input-stream-push-back-reader
  "Creates a PushbackReader from a given InputString"
  ([is]
     (input-stream-push-back-reader is 1))
  ([^InputStream is buf-len]
     (PushbackReader. (input-stream-reader is) (object-array buf-len) buf-len buf-len)))

(defn indexing-push-back-reader
  "Creates an IndexingPushbackReader from a given string or Reader"
  ([s-or-rdr]
     (indexing-push-back-reader s-or-rdr 1))
  ([s-or-rdr buf-len]
     (IndexingPushbackReader.
      (if (string? s-or-rdr) (string-push-back-reader s-or-rdr buf-len) s-or-rdr) 0 1 true nil)))

(defn read-line
  "Reads a line from the reader or from *in* if no reader is specified"
  ([] (read-line *in*))
  ([rdr]
     (if (or (instance? LineNumberingPushbackReader rdr)
             (instance? BufferedReader rdr))
       (clojure.core/read-line rdr)
       (loop [c (read-char rdr) s (StringBuilder.)]
         (if (newline? c)
           (str s)
           (recur (read-char rdr) (.append s c)))))))

(defn reader-error
  "Throws an Exception info, if rdr is an IndexingReader, additional information about column and line number is provided"
  [rdr & msg]
  (throw (ex-info (apply str msg)
                  (merge {:type :reader-exception}
                         (when (indexing-reader? rdr)
                           {:line (get-line-number rdr)
                            :column (get-column-number rdr)})))))
