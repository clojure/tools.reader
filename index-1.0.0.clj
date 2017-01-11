{:namespaces
 ({:doc "A clojure reader in clojure",
   :author "Bronsa",
   :name "clojure.tools.reader",
   :wiki-url "http://clojure.github.io/tools.reader/index.html",
   :source-url
   "https://github.com/clojure/tools.reader/blob/afbe6a5ef809db1b99f0b3dbeb7c99dba5daaadc/src/main/clojure/clojure/tools/reader.clj"}
  {:doc "An EDN reader in clojure",
   :author "Bronsa",
   :name "clojure.tools.reader.edn",
   :wiki-url
   "http://clojure.github.io/tools.reader/index.html#clojure.tools.reader.edn",
   :source-url
   "https://github.com/clojure/tools.reader/blob/5f0d3bd1f1cdcd648f8ff76663c1f087914d2cdf/src/main/clojure/clojure/tools/reader/edn.clj"}
  {:doc nil,
   :name "clojure.tools.reader.impl.commons",
   :wiki-url
   "http://clojure.github.io/tools.reader/index.html#clojure.tools.reader.impl.commons",
   :source-url
   "https://github.com/clojure/tools.reader/blob/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj"}
  {:doc "Protocols and default Reader types implementation",
   :author "Bronsa",
   :name "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader/index.html#clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj"}),
 :vars
 ({:raw-source-url
   "https://github.com/clojure/tools.reader/raw/afbe6a5ef809db1b99f0b3dbeb7c99dba5daaadc/src/main/clojure/clojure/tools/reader.clj",
   :name "*alias-map*",
   :file "src/main/clojure/clojure/tools/reader.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/afbe6a5ef809db1b99f0b3dbeb7c99dba5daaadc/src/main/clojure/clojure/tools/reader.clj#L333",
   :dynamic true,
   :line 333,
   :var-type "var",
   :arglists nil,
   :doc
   "Map from ns alias to ns, if non-nil, it will be used to resolve read-time\nns aliases instead of (ns-aliases *ns*).\n\nDefaults to nil",
   :namespace "clojure.tools.reader",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader/*alias-map*"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/afbe6a5ef809db1b99f0b3dbeb7c99dba5daaadc/src/main/clojure/clojure/tools/reader.clj",
   :name "map-func",
   :file "src/main/clojure/clojure/tools/reader.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/afbe6a5ef809db1b99f0b3dbeb7c99dba5daaadc/src/main/clojure/clojure/tools/reader.clj#L676",
   :line 676,
   :var-type "function",
   :arglists ([coll]),
   :doc
   "Decide which map type to use, array-map if less than 16 elements",
   :namespace "clojure.tools.reader",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader/map-func"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/afbe6a5ef809db1b99f0b3dbeb7c99dba5daaadc/src/main/clojure/clojure/tools/reader.clj",
   :name "resolve-symbol",
   :file "src/main/clojure/clojure/tools/reader.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/afbe6a5ef809db1b99f0b3dbeb7c99dba5daaadc/src/main/clojure/clojure/tools/reader.clj#L641",
   :dynamic true,
   :line 641,
   :var-type "function",
   :arglists ([s]),
   :doc
   "Resolve a symbol s into its fully qualified namespace version",
   :namespace "clojure.tools.reader",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader/resolve-symbol"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/5f0d3bd1f1cdcd648f8ff76663c1f087914d2cdf/src/main/clojure/clojure/tools/reader/edn.clj",
   :name "read",
   :file "src/main/clojure/clojure/tools/reader/edn.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/5f0d3bd1f1cdcd648f8ff76663c1f087914d2cdf/src/main/clojure/clojure/tools/reader/edn.clj#L355",
   :line 355,
   :var-type "function",
   :arglists
   ([]
    [reader]
    [{:keys [eof], :as opts} reader]
    [reader eof-error? eof opts]),
   :doc
   "Reads the first object from an IPushbackReader or a java.io.PushbackReader.\nReturns the object read. If EOF, throws if eof-error? is true otherwise returns eof.\nIf no reader is provided, *in* will be used.\n\nReads data in the edn format (subset of Clojure data):\nhttp://edn-format.org\n\nclojure.tools.reader.edn/read doesn't depend on dynamic Vars, all configuration\nis done by passing an opt map.\n\nopts is a map that can include the following keys:\n:eof - value to return on end-of-file. When not supplied, eof throws an exception.\n:readers  - a map of tag symbols to data-reader functions to be considered before default-data-readers.\n           When not supplied, only the default-data-readers will be used.\n:default - A function of two args, that will, if present and no reader is found for a tag,\n           be called with the tag and the value.",
   :namespace "clojure.tools.reader.edn",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.edn/read"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/5f0d3bd1f1cdcd648f8ff76663c1f087914d2cdf/src/main/clojure/clojure/tools/reader/edn.clj",
   :name "read-string",
   :file "src/main/clojure/clojure/tools/reader/edn.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/5f0d3bd1f1cdcd648f8ff76663c1f087914d2cdf/src/main/clojure/clojure/tools/reader/edn.clj#L413",
   :line 413,
   :var-type "function",
   :arglists ([s] [opts s]),
   :doc
   "Reads one object from the string s.\nReturns nil when s is nil or empty.\n\nReads data in the edn format (subset of Clojure data):\nhttp://edn-format.org\n\nopts is a map as per clojure.tools.reader.edn/read",
   :namespace "clojure.tools.reader.edn",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.edn/read-string"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :name "number-literal?",
   :file "src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj#L21",
   :line 21,
   :var-type "function",
   :arglists ([reader initch]),
   :doc
   "Checks whether the reader is at the start of a number literal",
   :namespace "clojure.tools.reader.impl.commons",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.impl.commons/number-literal?"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :name "parse-symbol",
   :file "src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj#L97",
   :line 97,
   :var-type "function",
   :arglists ([token]),
   :doc "Parses a string into a vector of the namespace and symbol",
   :namespace "clojure.tools.reader.impl.commons",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.impl.commons/parse-symbol"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :name "read-past",
   :file "src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj#L28",
   :line 28,
   :var-type "function",
   :arglists ([pred rdr]),
   :doc
   "Read until first character that doesn't match pred, returning\nchar.",
   :namespace "clojure.tools.reader.impl.commons",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.impl.commons/read-past"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :name "skip-line",
   :file "src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/7039837e121ff7d60b7e6e324a995bcca87f82b4/src/main/clojure/clojure/tools/reader/impl/commons.clj#L37",
   :line 37,
   :var-type "function",
   :arglists ([reader]),
   :doc "Advances the reader to the end of a line. Returns the reader",
   :namespace "clojure.tools.reader.impl.commons",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.impl.commons/skip-line"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "->IndexingPushbackReader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L115",
   :line 115,
   :var-type "function",
   :arglists
   ([rdr line column line-start? prev prev-column file-name]),
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.IndexingPushbackReader.",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/->IndexingPushbackReader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "->InputStreamReader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L59",
   :line 59,
   :var-type "function",
   :arglists ([is buf]),
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.InputStreamReader.",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/->InputStreamReader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "->PushbackReader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L80",
   :line 80,
   :var-type "function",
   :arglists ([rdr buf buf-len buf-pos]),
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.PushbackReader.",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/->PushbackReader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "->SourceLoggingPushbackReader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L246",
   :line 246,
   :var-type "function",
   :arglists
   ([rdr
     line
     column
     line-start?
     prev
     prev-column
     file-name
     source-log-frames]),
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.SourceLoggingPushbackReader.",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/->SourceLoggingPushbackReader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "->StringReader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L47",
   :line 47,
   :var-type "function",
   :arglists ([s s-len s-pos]),
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.StringReader.",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/->StringReader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "indexing-push-back-reader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L343",
   :line 343,
   :var-type "function",
   :arglists
   ([s-or-rdr] [s-or-rdr buf-len] [s-or-rdr buf-len file-name]),
   :doc
   "Creates an IndexingPushbackReader from a given string or PushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/indexing-push-back-reader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "indexing-reader?",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L304",
   :line 304,
   :var-type "function",
   :arglists ([rdr]),
   :doc "Returns true if the reader satisfies IndexingReader",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/indexing-reader?"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "input-stream-push-back-reader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L336",
   :line 336,
   :var-type "function",
   :arglists ([is] [is buf-len]),
   :doc "Creates a PushbackReader from a given InputStream",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/input-stream-push-back-reader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "input-stream-reader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L331",
   :line 331,
   :var-type "function",
   :arglists ([is]),
   :doc "Creates an InputStreamReader from an InputStream",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/input-stream-reader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "line-start?",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L411",
   :line 411,
   :var-type "function",
   :arglists ([rdr]),
   :doc
   "Returns true if rdr is an IndexingReader and the current char starts a new line",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/line-start?"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "log-source",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L402",
   :line 402,
   :var-type "macro",
   :arglists ([reader & body]),
   :doc
   "If reader is a SourceLoggingPushbackReader, execute body in a source\nlogging context. Otherwise, execute body, returning the result.",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/log-source"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "merge-meta",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L219",
   :line 219,
   :var-type "function",
   :arglists ([obj m]),
   :doc
   "Returns an object of the same type and value as `obj`, with its\nmetadata merged over `m`.",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/merge-meta"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "push-back-reader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L319",
   :line 319,
   :var-type "function",
   :arglists ([rdr] [rdr buf-len]),
   :doc "Creates a PushbackReader from a given reader or string",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/push-back-reader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "read-line",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L372",
   :line 372,
   :var-type "function",
   :arglists ([] [rdr]),
   :doc
   "Reads a line from the reader or from *in* if no reader is specified",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/read-line"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "reader-error",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L385",
   :line 385,
   :var-type "function",
   :arglists ([rdr & msg]),
   :doc
   "Throws an ExceptionInfo with the given message.\nIf rdr is an IndexingReader, additional information about column and line number is provided",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/reader-error"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "source-logging-push-back-reader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L353",
   :line 353,
   :var-type "function",
   :arglists
   ([s-or-rdr] [s-or-rdr buf-len] [s-or-rdr buf-len file-name]),
   :doc
   "Creates a SourceLoggingPushbackReader from a given string or PushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/source-logging-push-back-reader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "string-push-back-reader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L324",
   :line 324,
   :var-type "function",
   :arglists ([s] [s buf-len]),
   :doc "Creates a PushbackReader from a given string",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/string-push-back-reader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "string-reader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L314",
   :line 314,
   :var-type "function",
   :arglists ([s]),
   :doc "Creates a StringReader from a given string",
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/string-reader"}
  {:name "IndexingPushbackReader",
   :var-type "type",
   :namespace "clojure.tools.reader.reader-types",
   :arglists nil,
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/IndexingPushbackReader",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "InputStreamReader",
   :var-type "type",
   :namespace "clojure.tools.reader.reader-types",
   :arglists nil,
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/InputStreamReader",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "PushbackReader",
   :var-type "type",
   :namespace "clojure.tools.reader.reader-types",
   :arglists nil,
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/PushbackReader",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "SourceLoggingPushbackReader",
   :var-type "type",
   :namespace "clojure.tools.reader.reader-types",
   :arglists nil,
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/SourceLoggingPushbackReader",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "StringReader",
   :var-type "type",
   :namespace "clojure.tools.reader.reader-types",
   :arglists nil,
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/StringReader",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "IPushbackReader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L31",
   :line 31,
   :var-type "protocol",
   :arglists nil,
   :doc nil,
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/IPushbackReader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "IndexingReader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L35",
   :line 35,
   :var-type "protocol",
   :arglists nil,
   :doc nil,
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/IndexingReader"}
  {:raw-source-url
   "https://github.com/clojure/tools.reader/raw/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :name "Reader",
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/e130ceae22a96bb05b489dca33f7b5d7d4b2196d/src/main/clojure/clojure/tools/reader/reader_types.clj#L25",
   :line 25,
   :var-type "protocol",
   :arglists nil,
   :doc nil,
   :namespace "clojure.tools.reader.reader-types",
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/Reader"}
  {:name "unread",
   :doc "Pushes back a single character on to the stream",
   :var-type "function",
   :namespace "clojure.tools.reader.reader-types",
   :arglists ([reader ch]),
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/unread",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "get-column-number",
   :doc
   "Returns the column number of the next character to be read from the stream",
   :var-type "function",
   :namespace "clojure.tools.reader.reader-types",
   :arglists ([reader]),
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/get-column-number",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "get-file-name",
   :doc "Returns the file name the reader is reading from, or nil",
   :var-type "function",
   :namespace "clojure.tools.reader.reader-types",
   :arglists ([reader]),
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/get-file-name",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "get-line-number",
   :doc
   "Returns the line number of the next character to be read from the stream",
   :var-type "function",
   :namespace "clojure.tools.reader.reader-types",
   :arglists ([reader]),
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/get-line-number",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "peek-char",
   :doc
   "Returns the next char from the Reader without removing it from the reader stream",
   :var-type "function",
   :namespace "clojure.tools.reader.reader-types",
   :arglists ([reader]),
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/peek-char",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "read-char",
   :doc
   "Returns the next char from the Reader, nil if the end of stream has been reached",
   :var-type "function",
   :namespace "clojure.tools.reader.reader-types",
   :arglists ([reader]),
   :wiki-url
   "http://clojure.github.io/tools.reader//index.html#clojure.tools.reader.reader-types/read-char",
   :source-url nil,
   :raw-source-url nil,
   :file nil})}
