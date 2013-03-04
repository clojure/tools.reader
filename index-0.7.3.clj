{:namespaces
 ({:source-url
   "https://github.com/clojure/tools.reader/blob/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader-api.html",
   :name "clojure.tools.reader",
   :author "Bronsa",
   :doc "A clojure reader in clojure"}
  {:source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader.default-data-readers-api.html",
   :name "clojure.tools.reader.default-data-readers",
   :doc nil}
  {:source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/edn.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader.edn-api.html",
   :name "clojure.tools.reader.edn",
   :author "Bronsa",
   :doc "An EDN reader in clojure"}
  {:source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader.impl.commons-api.html",
   :name "clojure.tools.reader.impl.commons",
   :doc nil}
  {:source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/utils.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader.impl.utils-api.html",
   :name "clojure.tools.reader.impl.utils",
   :doc nil}
  {:source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader/clojure.tools.reader.reader-types-api.html",
   :name "clojure.tools.reader.reader-types",
   :doc nil}),
 :vars
 ({:name "*data-readers*",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj#L649",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/*data-readers*",
   :doc
   "Map from reader tag symbols to data reader Vars.\nReader tags without namespace qualifiers are reserved for Clojure.\nDefault reader tags are defined in clojure.tools.reader/default-data-readers\nand may be overridden by binding this Var.",
   :var-type "var",
   :line 649,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:name "*default-data-reader-fn*",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj#L656",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/*default-data-reader-fn*",
   :doc
   "When no data reader is found for a tag and *default-data-reader-fn*\nis non-nil, it will be called with two arguments, the tag and the value.\nIf *default-data-reader-fn* is nil (the default value), an exception\nwill be thrown for the unknown tag.",
   :var-type "var",
   :line 656,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:name "*read-eval*",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj#L631",
   :dynamic true,
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/*read-eval*",
   :doc
   "Defaults to true.\n\n***WARNING***\nThis setting implies that the full power of the reader is in play,\nincluding syntax that can cause code to execute. It should never be\nused with untrusted sources. See also: clojure.tools.reader.edn/read.\n\nWhen set to logical false in the thread-local binding,\nthe eval reader (#=) and *record/type literal syntax* are disabled in read/load.\nExample (will fail): (binding [*read-eval* false] (read-string \"#=(* 2 21)\"))\n\nWhen set to :unknown all reads will fail in contexts where *read-eval*\nhas not been explicitly bound to either true or false. This setting\ncan be a useful diagnostic tool to ensure that all of your reads\noccur in considered contexts.",
   :var-type "var",
   :line 631,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:file "src/main/clojure/clojure/tools/reader.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj#L663",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/default-data-readers",
   :namespace "clojure.tools.reader",
   :line 663,
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
   "https://github.com/clojure/tools.reader/blob/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj#L669",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/read",
   :doc
   "Reads the first object from an IPushbackReader or a java.io.PushbackReader.\nReturns the object read. If EOF, throws if eof-error? is true.\nOtherwise returns sentinel. If no stream is providen, *in* will be used.\n\n***WARNING***\nNote that read can execute code (controlled by *read-eval*),\nand as such should be used only with trusted sources.\n\nTo read data structures only, use clojure.tools.reader.edn/read\n\nNote that the function signature of clojure.tools.reader/read and\nclojure.tools.reader.edn/read is not the same for eof-handling",
   :var-type "function",
   :line 669,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:arglists ([s]),
   :name "read-string",
   :namespace "clojure.tools.reader",
   :source-url
   "https://github.com/clojure/tools.reader/blob/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj#L712",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/9000ff544c8d9e5e2f3de26e1540062e7c47f18c/src/main/clojure/clojure/tools/reader.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader/read-string",
   :doc
   "Reads one object from the string s.\nReturns nil when s is nil or empty.\n\n***WARNING***\nNote that read-string can execute code (controlled by *read-eval*),\nand as such should be used only with trusted sources.\n\nTo read data structures only, use clojure.tools.reader.edn/read-string\n\nNote that the function signature of clojure.tools.reader/read-string and\nclojure.tools.reader.edn/read-string is not the same for eof-handling",
   :var-type "function",
   :line 712,
   :file "src/main/clojure/clojure/tools/reader.clj"}
  {:file
   "src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj#L51",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.default-data-readers/parse-timestamp",
   :namespace "clojure.tools.reader.default-data-readers",
   :line 51,
   :var-type "var",
   :doc
   "Parse a string containing an RFC3339-like like timestamp.\n\nThe function new-instant is called with the following arguments.\n\n                min  max           default\n                ---  ------------  -------\n  years          0           9999      N/A (s must provide years)\n  months         1             12        1\n  days           1             31        1 (actual max days depends\n  hours          0             23        0  on month and year)\n  minutes        0             59        0\n  seconds        0             60        0 (though 60 is only valid\n  nanoseconds    0      999999999        0  when minutes is 59)\n  offset-sign   -1              1        0\n  offset-hours   0             23        0\n  offset-minutes 0             59        0\n\nThese are all integers and will be non-nil. (The listed defaults\nwill be passed if the corresponding field is not present in s.)\n\nGrammar (of s):\n\n  date-fullyear   = 4DIGIT\n  date-month      = 2DIGIT  ; 01-12\n  date-mday       = 2DIGIT  ; 01-28, 01-29, 01-30, 01-31 based on\n                            ; month/year\n  time-hour       = 2DIGIT  ; 00-23\n  time-minute     = 2DIGIT  ; 00-59\n  time-second     = 2DIGIT  ; 00-58, 00-59, 00-60 based on leap second\n                            ; rules\n  time-secfrac    = '.' 1*DIGIT\n  time-numoffset  = ('+' / '-') time-hour ':' time-minute\n  time-offset     = 'Z' / time-numoffset\n\n  time-part       = time-hour [ ':' time-minute [ ':' time-second\n                    [time-secfrac] [time-offset] ] ]\n\n  timestamp       = date-year [ '-' date-month [ '-' date-mday\n                    [ 'T' time-part ] ] ]\n\nUnlike RFC3339:\n\n  - we only parse the timestamp format\n  - timestamp can elide trailing components\n  - time-offset is optional (defaults to +00:00)\n\nThough time-offset is syntactically optional, a missing time-offset\nwill be treated as if the time-offset zero (+00:00) had been\nspecified.",
   :name "parse-timestamp"}
  {:file
   "src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj#L280",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.default-data-readers/read-instant-calendar",
   :namespace "clojure.tools.reader.default-data-readers",
   :line 280,
   :var-type "var",
   :doc
   "To read an instant as a java.util.Calendar, bind *data-readers* to a map with\nthis var as the value for the 'inst key.  Calendar preserves the timezone\noffset.",
   :name "read-instant-calendar"}
  {:file
   "src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj#L274",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.default-data-readers/read-instant-date",
   :namespace "clojure.tools.reader.default-data-readers",
   :line 274,
   :var-type "var",
   :doc
   "To read an instant as a java.util.Date, bind *data-readers* to a map with\nthis var as the value for the 'inst key. The timezone offset will be used\nto convert into UTC.",
   :name "read-instant-date"}
  {:file
   "src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj#L286",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.default-data-readers/read-instant-timestamp",
   :namespace "clojure.tools.reader.default-data-readers",
   :line 286,
   :var-type "var",
   :doc
   "To read an instant as a java.sql.Timestamp, bind *data-readers* to a\nmap with this var as the value for the 'inst key. Timestamp preserves\nfractional seconds with nanosecond precision. The timezone offset will\nbe used to convert into UTC.",
   :name "read-instant-timestamp"}
  {:arglists ([new-instance]),
   :name "validated",
   :namespace "clojure.tools.reader.default-data-readers",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj#L139",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/default_data_readers.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.default-data-readers/validated",
   :doc
   "Return a function which constructs and instant by calling constructor\nafter first validating that those arguments are in range and otherwise\nplausible. The resulting function will throw an exception if called\nwith invalid arguments.",
   :var-type "function",
   :line 139,
   :file
   "src/main/clojure/clojure/tools/reader/default_data_readers.clj"}
  {:arglists
   ([]
    [reader]
    [{:keys [eof], :as opts} reader]
    [reader eof-error? eof opts]),
   :name "read",
   :namespace "clojure.tools.reader.edn",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/edn.clj#L328",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/edn.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.edn/read",
   :doc
   "Reads the first object from an IPushbackReader or a java.io.PushbackReader.\nReturns the object read. If EOF, throws if eof-error? is true otherwise returns eof.\nIf no reader is provided, *in* will be used.\n\nReads data in the edn format (subset of Clojure data):\nhttp://edn-format.org\n\nclojure.tools.reader.edn/read doesn't depend on dynamic Vars, all configuration\nis done by passing an opt map.\n\nopts is a map that can include the following keys:\n:eof - value to return on end-of-file. When not supplied, eof throws an exception.\n:readers  - a map of tag symbols to data-reader functions to be considered before default-data-readers.\n           When not supplied, only the default-data-readers will be used.\n:default - A function of two args, that will, if present and no reader is found for a tag,\n           be called with the tag and the value.",
   :var-type "function",
   :line 328,
   :file "src/main/clojure/clojure/tools/reader/edn.clj"}
  {:arglists ([s] [opts s]),
   :name "read-string",
   :namespace "clojure.tools.reader.edn",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/edn.clj#L375",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/edn.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.edn/read-string",
   :doc
   "Reads one object from the string s.\nReturns nil when s is nil or empty.\n\nReads data in the edn format (subset of Clojure data):\nhttp://edn-format.org\n\nopts is a map as per clojure.tools.reader.edn/read",
   :var-type "function",
   :line 375,
   :file "src/main/clojure/clojure/tools/reader/edn.clj"}
  {:arglists ([reader initch]),
   :name "number-literal?",
   :namespace "clojure.tools.reader.impl.commons",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/commons.clj#L13",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.impl.commons/number-literal?",
   :doc
   "Checks whether the reader is at the start of a number literal",
   :var-type "function",
   :line 13,
   :file "src/main/clojure/clojure/tools/reader/impl/commons.clj"}
  {:arglists ([pred rdr]),
   :name "read-past",
   :namespace "clojure.tools.reader.impl.commons",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/commons.clj#L20",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.impl.commons/read-past",
   :doc
   "Read until first character that doesn't match pred, returning\nchar.",
   :var-type "function",
   :line 20,
   :file "src/main/clojure/clojure/tools/reader/impl/commons.clj"}
  {:arglists ([reader _]),
   :name "skip-line",
   :namespace "clojure.tools.reader.impl.commons",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/commons.clj#L29",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/commons.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.impl.commons/skip-line",
   :doc "Advances the reader to the end of a line. Returns the reader",
   :var-type "function",
   :line 29,
   :file "src/main/clojure/clojure/tools/reader/impl/commons.clj"}
  {:arglists ([ch]),
   :name "comment-prefix?",
   :namespace "clojure.tools.reader.impl.utils",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/utils.clj#L51",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/utils.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.impl.utils/comment-prefix?",
   :doc "Checks whether the character begins a comment.",
   :var-type "function",
   :line 51,
   :file "src/main/clojure/clojure/tools/reader/impl/utils.clj"}
  {:arglists ([ch]),
   :name "numeric?",
   :namespace "clojure.tools.reader.impl.utils",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/utils.clj#L45",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/utils.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.impl.utils/numeric?",
   :doc "Checks whether a given character is numeric",
   :var-type "function",
   :line 45,
   :file "src/main/clojure/clojure/tools/reader/impl/utils.clj"}
  {:arglists ([ch]),
   :name "whitespace?",
   :namespace "clojure.tools.reader.impl.utils",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/utils.clj#L38",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/impl/utils.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.impl.utils/whitespace?",
   :doc "Checks whether a given character is whitespace",
   :var-type "function",
   :line 38,
   :file "src/main/clojure/clojure/tools/reader/impl/utils.clj"}
  {:arglists ([rdr line column line-start? prev]),
   :name "->IndexingPushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L96",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/->IndexingPushbackReader",
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.IndexingPushbackReader.",
   :var-type "function",
   :line 96,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([is buf]),
   :name "->InputStreamReader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L48",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/->InputStreamReader",
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.InputStreamReader.",
   :var-type "function",
   :line 48,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([rdr buf buf-len buf-pos]),
   :name "->PushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L66",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/->PushbackReader",
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.PushbackReader.",
   :var-type "function",
   :line 66,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([s s-len s-pos]),
   :name "->StringReader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L36",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/->StringReader",
   :doc
   "Positional factory function for class clojure.tools.reader.reader_types.StringReader.",
   :var-type "function",
   :line 36,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([s-or-rdr] [s-or-rdr buf-len]),
   :name "indexing-push-back-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L188",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/indexing-push-back-reader",
   :doc
   "Creates an IndexingPushbackReader from a given string or Reader",
   :var-type "function",
   :line 188,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([is] [is buf-len]),
   :name "input-stream-push-back-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L181",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/input-stream-push-back-reader",
   :doc "Creates a PushbackReader from a given InputString",
   :var-type "function",
   :line 181,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([is]),
   :name "input-stream-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L176",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/input-stream-reader",
   :doc "Creates an InputStreamReader from an InputString",
   :var-type "function",
   :line 176,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([] [rdr]),
   :name "read-line",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L196",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/read-line",
   :doc
   "Reads a line from the reader or from *in* if no reader is specified",
   :var-type "function",
   :line 196,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([rdr & msg]),
   :name "reader-error",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L208",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/reader-error",
   :doc
   "Throws an ExceptionInfo with the given message.\nIf rdr is an IndexingReader, additional information about column and line number is provided",
   :var-type "function",
   :line 208,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([s] [s buf-len]),
   :name "string-push-back-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L169",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/string-push-back-reader",
   :doc "Creates a PushbackReader from a given string",
   :var-type "function",
   :line 169,
   :file "src/main/clojure/clojure/tools/reader/reader_types.clj"}
  {:arglists ([s]),
   :name "string-reader",
   :namespace "clojure.tools.reader.reader-types",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L164",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/string-reader",
   :doc "Creates a StringReader from a given string",
   :var-type "function",
   :line 164,
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
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L22",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/IPushbackReader",
   :namespace "clojure.tools.reader.reader-types",
   :line 22,
   :var-type "protocol",
   :doc nil,
   :name "IPushbackReader"}
  {:file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L26",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/IndexingReader",
   :namespace "clojure.tools.reader.reader-types",
   :line 26,
   :var-type "protocol",
   :doc nil,
   :name "IndexingReader"}
  {:file "src/main/clojure/clojure/tools/reader/reader_types.clj",
   :raw-source-url
   "https://github.com/clojure/tools.reader/raw/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj",
   :source-url
   "https://github.com/clojure/tools.reader/blob/547ff85b3bba887294e955e5e32a86a51c233379/src/main/clojure/clojure/tools/reader/reader_types.clj#L16",
   :wiki-url
   "http://clojure.github.com/tools.reader//clojure.tools.reader-api.html#clojure.tools.reader.reader-types/Reader",
   :namespace "clojure.tools.reader.reader-types",
   :line 16,
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
