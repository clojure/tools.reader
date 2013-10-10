{:namespaces
 ({:source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader-api.html",
   :name "clojure.tools.reader",
   :author "Bronsa",
   :doc "A clojure reader in clojure"}
  {:source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader/edn.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader.edn-api.html",
   :name "clojure.tools.reader.edn",
   :author "Bronsa",
   :doc "An EDN reader in clojure"}
  {:source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader.reader-types-api.html",
   :name "clojure.tools.reader.reader-types",
   :author "Bronsa",
   :doc "Protocols and default Reader types implementation"}),
 :vars
 ({:name "*data-readers*",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj#L657",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/*data-readers*",
   :doc
   "Map from reader tag symbols to data reader Vars.\nReader tags without namespace qualifiers are reserved for Clojure.\nDefault reader tags are defined in clojure.tools.reader/default-data-readers\nand may be overridden by binding this Var.",
   :var-type "var",
   :line 657,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:name "*default-data-reader-fn*",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj#L664",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/*default-data-reader-fn*",
   :doc
   "When no data reader is found for a tag and *default-data-reader-fn*\nis non-nil, it will be called with two arguments, the tag and the value.\nIf *default-data-reader-fn* is nil (the default value), an exception\nwill be thrown for the unknown tag.",
   :var-type "var",
   :line 664,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:name "*read-eval*",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj#L639",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/*read-eval*",
   :doc
   "Defaults to true.\n\n***WARNING***\nThis setting implies that the full power of the reader is in play,\nincluding syntax that can cause code to execute. It should never be\nused with untrusted sources. See also: clojure.tools.reader.edn/read.\n\nWhen set to logical false in the thread-local binding,\nthe eval reader (#=) and *record/type literal syntax* are disabled in read/load.\nExample (will fail): (binding [*read-eval* false] (read-string \"#=(* 2 21)\"))\n\nWhen set to :unknown all reads will fail in contexts where *read-eval*\nhas not been explicitly bound to either true or false. This setting\ncan be a useful diagnostic tool to ensure that all of your reads\noccur in considered contexts.",
   :var-type "var",
   :line 639,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:file "src/main/clojure/clojure/tools/reader.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj#L671",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/default-data-readers",
   :namespace "clojure.tools.reader",
   :line 671,
   :var-type "var",
   :doc
   "Default map of data reader functions provided by Clojure.\nMay be overridden by binding *data-readers*",
   :name "default-data-readers"}
  {:arglists
   ([]
    [reader]
    [reader eof-error? sentinel]
    [reader eof-error? sentinel recursive?]),
   :name "read",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj#L677",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/read",
   :doc
   "Reads the first object from an IPushbackReader or a java.io.PushbackReader.\nReturns the object read. If EOF, throws if eof-error? is true.\nOtherwise returns sentinel. If no stream is providen, *in* will be used.\n\n***WARNING***\nNote that read can execute code (controlled by *read-eval*),\nand as such should be used only with trusted sources.\n\nTo read data structures only, use clojure.tools.reader.edn/read\n\nNote that the function signature of clojure.tools.reader/read and\nclojure.tools.reader.edn/read is not the same for eof-handling",
   :var-type "function",
   :line 677,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:arglists ([s]),
   :name "read-string",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj#L720",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/read-string",
   :doc
   "Reads one object from the string s.\nReturns nil when s is nil or empty.\n\n***WARNING***\nNote that read-string can execute code (controlled by *read-eval*),\nand as such should be used only with trusted sources.\n\nTo read data structures only, use clojure.tools.reader.edn/read-string\n\nNote that the function signature of clojure.tools.reader/read-string and\nclojure.tools.reader.edn/read-string is not the same for eof-handling",
   :var-type "function",
   :line 720,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:arglists ([form]),
   :name "syntax-quote",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj#L736",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/syntax-quote",
   :doc "Macro equivalent to the syntax-quote reader macro (`).",
   :var-type "macro",
   :line 736,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:arglists
   ([]
    [reader]
    [{:keys [eof], :as opts} reader]
    [reader eof-error? eof opts]),
   :name "read",
   :namespace "clojure.tools.reader.edn",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader/edn.clj#L336",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader/edn.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.edn/read",
   :doc
   "Reads the first object from an IPushbackReader or a java.io.PushbackReader.\nReturns the object read. If EOF, throws if eof-error? is true otherwise returns eof.\nIf no reader is provided, *in* will be used.\n\nReads data in the edn format (subset of Clojure data):\nhttp://edn-format.org\n\nclojure.tools.reader.edn/read doesn't depend on dynamic Vars, all configuration\nis done by passing an opt map.\n\nopts is a map that can include the following keys:\n:eof - value to return on end-of-file. When not supplied, eof throws an exception.\n:readers  - a map of tag symbols to data-reader functions to be considered before default-data-readers.\n           When not supplied, only the default-data-readers will be used.\n:default - A function of two args, that will, if present and no reader is found for a tag,\n           be called with the tag and the value.",
   :var-type "function",
   :line 336,
   :file "src/main/clojure/clojure/tools/reader/edn.clj"}
  {:arglists ([s] [opts s]),
   :name "read-string",
   :namespace "clojure.tools.reader.edn",
   :source-url
   "https://github.com/clojure/tools.reader/blob/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader/edn.clj#L383",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/54546edff7566c544e2aad5b225617b2a557b1c7/src/main/clojure/clojure/tools/reader/edn.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.edn/read-string",
   :doc
   "Reads one object from the string s.\nReturns nil when s is nil or empty.\n\nReads data in the edn format (subset of Clojure data):\nhttp://edn-format.org\n\nopts is a map as per clojure.tools.reader.edn/read",
   :var-type "function",
   :line 383,
   :file "src/main/clojure/clojure/tools/reader/edn.clj"}
  {:arglists ([rdr line column line-start? prev file-name]),
   :name "->IndexingPushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L107",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/->IndexingPushbackReader",
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.IndexingPushbackReader.",
   :var-type "function",
   :line 107,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([is buf]),
   :name "->InputStreamReader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L58",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/->InputStreamReader",
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.InputStreamReader.",
   :var-type "function",
   :line 58,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([rdr buf buf-len buf-pos]),
   :name "->PushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L76",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/->PushbackReader",
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.PushbackReader.",
   :var-type "function",
   :line 76,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([s s-len s-pos]),
   :name "->StringReader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L46",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/->StringReader",
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.StringReader.",
   :var-type "function",
   :line 46,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists
   ([s-or-rdr] [s-or-rdr buf-len] [s-or-rdr buf-len file-name]),
   :name "indexing-push-back-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L202",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/indexing-push-back-reader",
   :doc
   "Creates an IndexingPushbackReader from a given string or Reader",
   :var-type "function",
   :line 202,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([rdr]),
   :name "indexing-reader?",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L168",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/indexing-reader?",
   :doc "Returns true if the reader satisfies IndexingReader",
   :var-type "function",
   :line 168,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([is] [is buf-len]),
   :name "input-stream-push-back-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L195",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/input-stream-push-back-reader",
   :doc "Creates a PushbackReader from a given InputStream",
   :var-type "function",
   :line 195,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([is]),
   :name "input-stream-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L190",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/input-stream-reader",
   :doc "Creates an InputStreamReader from an InputStream",
   :var-type "function",
   :line 190,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([] [rdr]),
   :name "read-line",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L212",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/read-line",
   :doc
   "Reads a line from the reader or from *in* if no reader is specified",
   :var-type "function",
   :line 212,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([rdr & msg]),
   :name "reader-error",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L224",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/reader-error",
   :doc
   "Throws an ExceptionInfo with the given message.\nIf rdr is an IndexingReader, additional information about column and line number is provided",
   :var-type "function",
   :line 224,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([s] [s buf-len]),
   :name "string-push-back-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L183",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/string-push-back-reader",
   :doc "Creates a PushbackReader from a given string",
   :var-type "function",
   :line 183,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([s]),
   :name "string-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L178",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/string-reader",
   :doc "Creates a StringReader from a given string",
   :var-type "function",
   :line 178,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/IndexingPushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "type",
   :name "IndexingPushbackReader"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/InputStreamReader",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "type",
   :name "InputStreamReader"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/PushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "type",
   :name "PushbackReader"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/StringReader",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "type",
   :name "StringReader"}
  {:file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L30",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/IPushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :line 30,
   :var-type "protocol",
   :doc nil,
   :name "IPushbackReader"}
  {:file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L34",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/IndexingReader",
   :namespace "clojure.tools.reader.reader-types",
   :line 34,
   :var-type "protocol",
   :doc nil,
   :name "IndexingReader"}
  {:file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/f7f71d88838f2a146baa236eac0f066a09156950/src/main/clojure/clojure/tools/reader/reader_types.clj#L24",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/Reader",
   :namespace "clojure.tools.reader.reader-types",
   :line 24,
   :var-type "protocol",
   :doc nil,
   :name "Reader"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/unread",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "function",
   :arglists ([reader ch]),
   :doc "Pushes back a single character on to the stream",
   :name "unread"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/get-column-number",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "function",
   :arglists ([reader]),
   :doc
   "Returns the line number of the next character to be read from the stream",
   :name "get-column-number"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/get-file-name",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "function",
   :arglists ([reader]),
   :doc "Returns the file name the reader is reading from, or nil",
   :name "get-file-name"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/get-line-number",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "function",
   :arglists ([reader]),
   :doc
   "Returns the line number of the next character to be read from the stream",
   :name "get-line-number"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/peek-char",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "function",
   :arglists ([reader]),
   :doc
   "Returns the next char from the Reader without removing it from the reader stream",
   :name "peek-char"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/read-char",
   :namespace "clojure.tools.reader.reader-types",
   :var-type "function",
   :arglists ([reader]),
   :doc
   "Returns the next char from the Reader, nil if the end of stream has been reached",
   :name "read-char"})}
