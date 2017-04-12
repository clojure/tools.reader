(ns cljs.tools.reader.errors
  (:require [cljs.tools.reader.reader-types :as types]
            [cljs.tools.reader.inspect :as i]))

;;tbd some exceptions should have type :illegal-argument

#_(defn- location-details [rdr]
  (let [details {:type :reader-exception}]
    (if (types/indexing-reader? rdr)
      (assoc
        details
        :file (types/get-file-name rdr)
        :line (types/get-line-number rdr)
        :col (types/get-column-number rdr))
      details)))

(defn- ex-details
  [rdr ex-type]
    (let [details {:type ex-type}]
      (if (types/indexing-reader? rdr)
        (assoc
          details
          :file (types/get-file-name rdr)
          :line (types/get-line-number rdr)
          :col (types/get-column-number rdr))
        details)))

(defn- throw-ex
  "Throw an ex-info error."
  [rdr ex-type & msg]
  (let [details (ex-details rdr ex-type)
        file (:file details)
        line (:line details)
        col (:col details)
        msg1 (if file (str file " "))
        msg2 (if line (str "[line " line ", col " col "]"))
        msg3 (if (or msg1 msg2) " ")
        full-msg (apply str msg1 msg2 msg3 msg)]
    (throw (ex-info full-msg details))))

(defn reader-error
  "Throws an ExceptionInfo with the given message.
   If rdr is an IndexingReader, additional information about column and line number is provided"
   [rdr & msgs]
  (throw-ex rdr :reader-exception (apply str msgs)))

(defn illegal-arg-error
  "Throws an IllegalArgument with the given message.
   If rdr is an IndexingReader, additional information about column and line number is provided"
   [rdr & msgs]
  (throw-ex rdr :illegal-argument (apply str msgs)))

(defn throw-eof-delimited
  ([rdr kind line] (throw-eof-delimited rdr kind line nil))
  ([rdr kind line n]
    (reader-error
      rdr
      "Unexpected EOF while reading "
      (if n
        (str "item " n " of "))
      kind
      (if line
        (str ", starting at line " line))
      ".")))

(defn throw-odd-map [rdr line col elements]
  (reader-error
    rdr
    "The map literal starting with "
    (i/inspect (first elements))
    (if line (str " on line " line " column " col))
    " contains "
    (count elements)
    " form(s). Map literals must contain an even number of forms."))

(defn throw-invalid-number [rdr token]
  (reader-error
    rdr
    "Invalid number: "
    token
    "."))

(defn throw-invalid-unicode-literal [rdr token]
  (throw
    (illegal-arg-error
      rdr
      "Invalid unicode literal: \\"
      token
      ".")))

(defn throw-invalid-unicode-escape [rdr ch]
  (reader-error
    rdr
    "Invalid unicode escape: \\u"
    ch
    "."))

(defn throw-invalid [rdr kind token]
  (reader-error rdr "Invalid " kind ": " token "."))

(defn throw-eof-at-start [rdr kind]
  (reader-error rdr "Unexpected EOF while reading start of " kind "."))

(defn throw-bad-char [rdr kind ch]
  (reader-error rdr "Invalid character: " ch " found while reading " kind "."))

(defn throw-eof-at-dispatch [rdr]
  (reader-error rdr "Unexpected EOF while reading dispatch character."))

(defn throw-bad-dispatch [rdr ch]
  (reader-error rdr "No dispatch macro for " ch "."))

(defn throw-unmatch-delimiter [rdr ch]
  (reader-error rdr "Unmatched delimiter " ch "."))

(defn throw-eof-reading [rdr kind & start]
  (reader-error rdr "Unexpected EOF reading " kind " starting " (apply str start) "."))

(defn throw-no-dispatch [rdr ch]
  (throw-bad-dispatch rdr ch))

(defn throw-invalid-unicode-char[rdr token]
  (reader-error
    rdr
    "Invalid unicode character \\"
    token
    "."))

(defn throw-invalid-unicode-digit-in-token[rdr ch token]
  (illegal-arg-error
    rdr
    "Invalid digit "
    ch
    " in unicode character \\"
    token
    "."))

(defn throw-invalid-unicode-digit[rdr ch]
  (illegal-arg-error
    rdr
    "Invalid digit "
    ch
    " in unicode character."))

(defn throw-invalid-unicode-len[rdr actual expected]
  (illegal-arg-error
    rdr
    "Invalid unicode literal. Unicode literals should be "
    expected
    "characters long.  "
    "value suppled is "
    actual
    "characters long."))

(defn throw-invalid-character-literal[rdr token]
  (reader-error rdr "Invalid character literal \\u" token "."))

(defn throw-invalid-octal-len[rdr token]
  (reader-error
    rdr
    "Invalid octal escape sequence in a character literal:"
    token
   ". Octal escape sequences must be 3 or fewer digits."))

(defn throw-bad-octal-number [rdr]
  (reader-error rdr "Octal escape sequence must be in range [0, 377]."))

(defn throw-unsupported-character[rdr token]
  (reader-error
    rdr
    "Unsupported character: "
    token
   "."))

(defn throw-eof-in-character[rdr]
  (reader-error
    rdr
    "Unexpected EOF while reading character."))


(defn throw-bad-escape-char [rdr ch]
  (reader-error rdr "Unsupported escape character: \\" ch "."))

(defn throw-single-colon [rdr]
  (reader-error rdr "A single colon is not a valid keyword."))


(defn throw-bad-metadata [rdr x]
  (reader-error
    rdr
    "Metadata cannot be "
    (i/inspect x)
    ". Metadata must be a Symbol, Keyword, String or Map."))

(defn throw-bad-metadata-target [rdr target]
  (reader-error
    rdr
    "Metadata can not be applied to "
    (i/inspect target)
    ". "
    "Metadata can only be applied to IMetas."))

(defn throw-feature-not-keyword [rdr feature]
  (reader-error
    rdr
    "Feature cannot be "
    (i/inspect feature)
    " Features must be keywords."))


(defn throw-ns-map-no-map [rdr ns-name]
  (reader-error rdr "Namespaced map with namespace " ns-name " does not specify a map."))

(defn throw-bad-ns [rdr ns-name]
  (reader-error rdr "Invalid value used as namespace in namespaced map: " ns-name "."))

(defn throw-bad-reader-tag [rdr tag]
  (reader-error
    rdr
    "Invalid reader tag: "
    (i/inspect tag)
    ". Reader tags must be symbols."))


(defn throw-unknown-reader-tag [rdr tag]
  (reader-error
    rdr
    "No reader function for tag "
    (i/inspect tag)
    "."))

(defn- duplicate-keys-error [msg coll]
  (letfn [(duplicates [seq]
            (for [[id freq] (frequencies seq)
                  :when (> freq 1)]
              id))]
    (let [dups (duplicates coll)]
      (apply str msg
             (when (> (count dups) 1) "s")
             ".: " (interpose ", " dups)))))

(defn throw-dup-keys [rdr kind ks]
  (reader-error
    rdr
    (duplicate-keys-error
      (str  kind " literal contains duplicate key")
      ks)))
