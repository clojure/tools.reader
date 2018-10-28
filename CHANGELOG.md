Changelog
========================================
* Release 1.3.2 on 26 Oct 2016
  * Fix bad arities for read+string
* Release 1.3.1 on 22 Oct 2018
  * Changes to read+string 2-arity to match new clojure arity (#CLJ-2358)
  * Fix buffer overflow when reading files with consecutive carriage returns (#TRDR-54)
* Release 1.3.0 on 5 Jul 2018
  * Fix private var warning in cljs impl
* Release 1.3.0-alpha3 on 25 Feb 2018
  * Improve memory footprint of source-logging-pushback-reader
  * Trim output of read+string to match clojure impl
  * Workaround for bad read+string 2-arity
* Release 1.3.0-alpha2 on 25 Feb 2018
  * Reduce memory footprint of source-logging-pushback-reader by cleaning up buffer after use
  * Exclude read+string from import to remove warning when using clojure 1.9.0
* Release 1.3.0-alpha1 on 24 Feb 2018
  * Implement read+string
  * Attach metadata to all IObjs instead of requiring IMeta
* Release 1.2.2 on 18 Feb 2018
  * Exclude reader-conditional and tagged-literal import to remove warning when using newer versions of clojure
* Release 1.2.1 on 25 Jan 2018
  * Fix potential stack overflow introduced by previous release
* Release 1.2.0 on 25 Jan 2018
  * Fixed wrong recurson point in read, that caused source logging issues
* Release 1.1.3.1 on 19 Jan 2017
  * Fix malformed error message for cljs reader (#TRDR-51)
* Release 1.1.2 on 17 Jan 2017
  * Fixed cljs edn reader wrt unicode limits (#TRDR-50)
* Release 1.1.1 on 07 Nov 2017
  * Fix typo in cljs inspector (#TRDR-46)
  * Fix typo in octal string validation (#TRDR-47)
* Release 1.1.0 on 09 Sept 2017
  * Remove +-Inf/NaN experimental feature
  * Add support for ##Inf ##-Inf and ##NaN (#CLJ-1074)
* Release 1.0.6 on 29 Aug 2017
  * Disallow non-aliases after ::
* Release 1.0.5 on 03 Aug 2017
  * Syntax-quote delegates `Foo.` symbols to `resolve-symbol`, cljs needs to resolve namespaces while clj classes
* Release 1.0.3 on 19 Jul 2017
  * ALl errors throw ex-info, add :ex-kind :eof for EOF errors
* Release 1.0.2 on 11 Jul 2017
  * Improved error messages (#TRDR-44)
  * Duplicate checks for sets in CLJS port
* Release 1.0.1 on 08 Jul 2017
  * More informative error messages for tagged literals
  * Ported the Fix for #TRDR-44 in the edn namespace
* Release 1.0.0 on 08 Jun 2017
* Release 1.0.0-RC1 on 24 May 2017
  * Throw on nil ns in namespaced map (#CLJ-2152)
  * Better map keys duplicate checks in cljs port
* Release 1.0.0-beta4 on 13 Dec 2016
  * Respect array-map/hash-map in cljs port (#TRDR-40)
* Release 1.0.0-beta3 on 25 Jun 2016
  * Initial implementation of new namespaced maps support
  * Removed further boxed math
  * Droped clojure 1.4.0 support
* Release 1.0.0-beta2 on 10 Jun 2016
  * Removed some boxed math
* Release 1.0.0-beta1 on 30 Mar 2016
  * Error out on invalid numbers
* Release 1.0.0-alpha4 on 29 Feb 2016
  * Throw exception on duplicate keys in sets/maps in cljs port
* Release 1.0.0-alpha3 on 05 Jan 2016
  * Auto qualify symbols in cljs syntax-quote
* Release 1.0.0-alpha2 on 05 Dec 2015
  * Renamed pushback-reader to push-back-reader
  * Added Closeable type hint to reader ctor functions
* Release 1.0.0-alpha1 on 02 Nov 2015
  * Reader types implement Closeable
  * Added pushback-reader
  * Improved interoperability between reader types and java readers
* Release 0.10.0 on 09 Oct 2015
  * Better error for invalid octal literal
* Release 0.10.0-alpha3 on 28 Jul 2015
  * Updated cljs port for self-hosted cljs
* Release 0.10.0-alpha2 on 27 Jun 2015
  * Re-added *alias-map* support in cljs port
  * Added support for auto-qualifying symbols and keywords inside backtick in cljs port
  * Removed a reflection instance
  * Allow returning nil in reader-conditionals (#TRDR-30)
* Release 0.10.0-alpha1 on 09 Jun 2015
  * Initial cljs port
  * Removed support for clojure 1.3.0
* Release 0.9.2 on 23 Apr 2015
  * Allow sym->sym mapping in *alias-map*
* Release 0.9.1 on 09 Apr 2015
  * Fixed tagged literals inside read-cond
* Release 0.9.0 on 09 Apr 2015
  * Implemented reader conditionals
  * Fixed column metadata on sets
  * Disallowed reading keywords and symbols ending with colon
  * Made ExceptionInfo extend RuntimeException
* Release 0.8.16 on 06 Mar 2015
  * Added new output jar with "aot" classifier
* Release 0.8.15 on 21 Feb 2015
  * Fixed line-start? offset
* Release 0.8.14 on 20 Feb 2015
  * Added line-start? predicate
  * Removed support for \xHH chars
  * Fixed column metadata handling for set literals
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
