Public API
========================================

There are four public namespaces:
* [clojure.tools.reader.reader-types](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types) offers protocols, implementations and convenient function helpers for some reader types.
  * Protocols:
     * [Reader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/Reader) ([read-char](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/read-char), [peek-char](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/peek-char))
     * [IPushbackReader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/IPushbackReader) ([unread](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/unread))
     * [IndexingReader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/IndexingReader) ([get-line-number](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/get-line-number), [get-column-number](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/get-column-number), [get-file-name](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/get-file-name))
  * Types:
     * [StringReader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/StringReader)
     * [PushbackReader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/PushbackReader)
     * [IndexingPushbackReader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/IndexingPushbackReader)
     * [InputStreamReader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/InputStreamReader)
     * [SourceLoggingPushbackReader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/SourceLoggingPushbackReader)
  * Types Constructor Functions:
     * [string-reader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/string-reader)
     * [string-push-back-reader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/string-push-back-reader)
     * [indexing-push-back-reader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/indexing-push-back-reader)
     * [input-stream-reader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/input-stream-reader)
     * [input-stream-push-back-reader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/input-stream-push-back-reader)
     * [source-logging-push-back-reader](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/source-logging-push-back-reader)
  * Functions:
     * [read-line](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/read-line)
     * [reader-error](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.reader-types/reader-error)
  * Macros:
     * [log-source](http://clojure.github.io/tools.reader/index.html#clojure.tools.reader.reader-types/log-source)
* [clojure.tools.reader.edn](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.edn) offers a feature-complete EDN reader, whose API matches clojure.edn's one, those functions are *safe*.
  * Functions:
     * [read](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.edn/read)
     * [read-string](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.edn/read-string)
* [clojure.tools.reader](http://clojure.github.com/tools.reader/index.html#toc0) offers a feature-complete clojure reader, whose API matches clojure.core's one, those functions are *unsafe* and may allow code execution if not used properly.
  * Functions:
     * [read](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader/read)
     * [read-string](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader/read-string)
  * Macros:
     * [syntax-quote](http://clojure.github.io/tools.reader/index.html#clojure.tools.reader/syntax-quote)
  * Vars:
     * [\*read-eval\*](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader/*read-eval*)
     * [\*data-readers\*](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader/*data-readers*)
     * [\*default-data-reader-fn\*](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader/*default-data-reader-fn*)
     * [default-data-readers](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader/default-data-readers)
* [clojure.tools.reader.default-data-readers](http://clojure.github.com/tools.reader/index.html#clojure.tools.reader.default-data-readers) offers implementations for the #inst and #uuid tagged literals, copied from the clojure source.

Refer to docstrings in each namespace and to the project's [autodoc](http://clojure.github.com/tools.reader/index.html) for more documentation.
