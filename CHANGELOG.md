Changelog
========================================
* Release 0.8.13 on 04 Dec 2014
  * Fixed column number handling in source info
* Release 0.8.12 on 03 Nov 2014
  * Fixed a bug when reading syntax-quoted map literals containing a :val key
* Release 0.8.11 on 31 Oct 2014
  * Ensure metadata is not lost in syntax-quoted forms
* Release 0.8.10 on 23 Oct 2014
  * Reverted hack to allow `{~@foo} and `#{~(gensym) ~(gensym)}
  * Fixed error reporting for keywords starting with macro-starting chars
* Release 0.8.9 on 18 Sep 2014
  * Don't attach metadata on syntax-quoted forms if it's composed only by source-info
* Release 0.8.8 on 31 Aug 2014
  * Made resolve-symbol a public ^:dynamic function
* Release 0.8.7 on 23 Aug 2014
  * Fixed a bug introduced in 0.8.6 in syntax-quote
  * Cleaned up read-eval implementation
* Release 0.8.6 on 16 Aug 2014 (BROKEN)
  * Allow for `{~@foo} and `#{~(gensym) ~(gensym)}
  * Performance enhancements in the code emitted by syntax-quote
* Release 0.8.5 on 12 Jun 2014
  * Attach source info on literal sets too
  * Fixed how syntax-quoting handles splicing empty seqs into a list
* Release 0.8.4 on 04 Apr 2014
  * Fixed misplaced docstring
  * Added file metadata info
  * Fixed end-column offset
* Release 0.8.3 on 20 Dec 2013
  * Fixed a typo that caused syntax-quote to keep unqualified the classes in static calls
* Release 0.8.2 on 11 Dec 2013
  * Fixed read-line implementation fallbacking to c.c/read-line
  * Richer error reporting
* Release 0.8.1 on 05 Dec 2013
  * Fixed possible stack overflow in read (#TRDR-11)
* Release 0.8.0 on 20 Nov 2013
  * Added SourceLoggingPushbackReader (#TRDR-9)
  * Added end-line/column-line metadata info (#TRDR-10)
* Release 0.7.10 on 24 Oct 2013
  * Added \*alias-map\*
* Release 0.7.9 on 11 Oct 2013
  * Fixed \r\n handling
* Release 0.7.8 on 27 Sep 2013
  * Added missing whitespace in exception message
  * Added get-file-name to IndexingReader
* Release 0.7.7 on 13 Sep 2013
  * Fixed #TRDR-8
  * Fixed out-of-bound exception in ctor-reader args mismatch
* Release 0.7.6 on 14 Aug 2013
  * Fixed #TRDR-7
* Release 0.7.5 on 03 Jun 2013
  * Removed all reflection for clojure 1.3
  * Fixed :column metadata handling on symbols
* Release 0.7.4 on 19 Apr 2013
  * Fixed docstrings position and typos
  * Fixed a bug that would have prevented future alpha versions of clojure to have column metadata
  * Made symbols containing two consecutive ':' illegal, as per [clojure.org](http://clojure.org/reader#The%20Reader--Reader%20forms)
  * Made symbols containing two consecutive '/' illegal
  * Removed reflection warnings
* Release 0.7.3 on Mar 08, 2013
  * AOT compile only ExceptionInfo.
* Release 0.7.2 on 02 Mar 2013
  * Fixed evaling read objects for clojure 1.5
* Release 0.7.1 on 02 Mar 2013
  * Added the syntax-quote macro to the public API
* Release 0.7.0 on 14 Feb 2013
  * Fixed #TRDR-1 by @jafingerhut
  * Made compatible with clojure-1.3.0
  * Decoupled from clojure.core vars (\*read-eval\*, \*default-data-reader-fn\*, \*data-readers\*)
  * clojure.tools.reader/read-string and clojure.tools.reader.edn/read-string return nil if string is nil or empty
  * Added comprehensive docstrings
* Release 0.6.5 on 09 Feb 2013
  * Fixed reading \@ \~ and \`
* Release 0.6.4 on 08 Feb 2013
  * Fixed Unicode char reading
  * Added \*default-data-reader-fn\* support
  * Added an EDN-only reader
  * Disabled record literals reading when \*read-eval\* is bound to false
  * Made \% a symbol constituent char
  * Made the EDN reader API match the clojure.edn one
* Release 0.6.2 on 04 Feb 2013
  * Added line/column metadata on vectors, maps and symbols
* Release 0.6.0 on 03 Feb 2013
  * Initial release.
