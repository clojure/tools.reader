Changelog
========================================
* Release 0.8.4 on Apr 04, 2014
  * Fixed misplaced docstring
  * Added file metadata info
  * Fixed end-column offset
* Release 0.8.3 on Dec 20, 2013
  * Fixed a typo that caused syntax-quote to keep unqualified the classes in static calls
* Release 0.8.2 on Dec 11, 2013
  * Fixed read-line implementation fallbacking to c.c/read-line
  * Richer error reporting
* Release 0.8.1 on Dec 05, 2013
  * Fixed possible stack overflow in read (#TRDR-11)
* Release 0.8.0 on Nov 20, 2013
  * Added SourceLoggingPushbackReader (#TRDR-9)
  * Added end-line/column-line metadata info (#TRDR-10)
* Release 0.7.10 on Oct 24, 2013
  * Added \*alias-map\*
* Release 0.7.9 on Oct 11, 2013
  * Fixed \r\n handling
* Release 0.7.8 on Sep 27, 2013
  * Added missing whitespace in exception message
  * Added get-file-name to IndexingReader
* Release 0.7.7 on Sep 13, 2013
  * Fixed #TRDR-8
  * Fixed out-of-bound exception in ctor-reader args mismatch
* Release 0.7.6 on Aug 14, 2013
  * Fixed #TRDR-7
* Release 0.7.5 on Jun 03, 2013
  * Removed all reflection for clojure 1.3
  * Fixed :column metadata handling on symbols
* Release 0.7.4 on Apr 19, 2013
  * Fixed docstrings position and typos
  * Fixed a bug that would have prevented future alpha versions of clojure to have column metadata
  * Made symbols containing two consecutive ':' illegal, as per [clojure.org](http://clojure.org/reader#The%20Reader--Reader%20forms)
  * Made symbols containing two consecutive '/' illegal
  * Removed reflection warnings
* Release 0.7.3 on Mar 08, 2013
  * AOT compile only ExceptionInfo.
* Release 0.7.2 on Mar 02, 2013
  * Fixed evaling read objects for clojure 1.5
* Release 0.7.1 on Mar 02, 2013
  * Added the syntax-quote macro to the public API
* Release 0.7.0 on Feb 14, 2013
  * Fixed #TRDR-1 by @jafingerhut
  * Made compatible with clojure-1.3.0
  * Decoupled from clojure.core vars (\*read-eval\*, \*default-data-reader-fn\*, \*data-readers\*)
  * clojure.tools.reader/read-string and clojure.tools.reader.edn/read-string return nil if string is nil or empty
  * Added comprehensive docstrings
* Release 0.6.5 on Feb 09, 2013
  * Fixed reading \@ \~ and \`
* Release 0.6.4 on Feb 08, 2013
  * Fixed Unicode char reading
  * Added \*default-data-reader-fn\* support
  * Added an EDN-only reader
  * Disabled record literals reading when \*read-eval\* is bound to false
  * Made \% a symbol constituent char
  * Made the EDN reader API match the clojure.edn one
* Release 0.6.2 on Feb 04, 2013
  * Added line/column metadata on vectors, maps and symbols
* Release 0.6.0 on Feb 03, 2013
  * Initial release.
