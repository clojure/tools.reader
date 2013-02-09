clojure.tools.reader
========================================

A complete Clojure reader and an EDN-only reader.

Public API
========================================

There are three public namespaces:
* `clojure.tools.reader.reader-types`, offers protocols, implementations and conveniente constructors for some reader types.
* `clojure.tools.reader.edn` offers a feature-complete EDN reader, whose api matches clojure.edn's one
* `clojure.tools.reader` offers a feature-complete clojure reader, whose api matches clojure.core's one

Refer to docstrings in each namespace for more documentation.

Releases and Dependency Information
========================================

Latest stable release: 0.6.5

* [All Released Versions](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22tools.reader%22)

* [Development Snapshot Versions](https://oss.sonatype.org/index.html#nexus-search;gav%7Eorg.clojure%7Etools.reader%7E%7E%7E)

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clojure
[org.clojure/tools.reader "0.6.5"]
```
[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>tools.reader</artifactId>
  <version>0.6.5</version>
</dependency>
```
Example Usage
========================================

```clojure
(require '[clojure.tools.reader.reader-types :as r-t]
         '[clojure.tools.reader :as r])
;;=> nil

(def reader (r-t/string-push-back-reader "1"))
;;=> #'user/reader

(r-t/read-char reader)
;;=> \1

(r-t/unread reader \1)
;;=> \1

(r/read reader)
;;=> 1

(r/read-string "2")
;;=> 2
```

Differences from LispReader.java
========================================

There are small differences from clojure.lang.LispReader:

* `t.r/read` throws an `ex-info` for almost every exception, whereas `clojure.lang.LispReader/read` throws a `ReaderException` wrapping the causing exception.

* `t.r/read` is capable of reading `\x` escaped chars

* `t.r/read` is capable of reading `Infinity` `+Infinity` `-Infinity` and `NaN` as per #CLJ-1074

* `t.r/read` is capable of reading literal tags contaning periods, fixing #CLJ-1100

* `t.r/read-line` has an additional arity with which is possible to specify the reader to read from

TODO
========================================

- More consistent error handling
- Better documentation
- Port to clojurescript

Changelog
========================================

* Release 0.6.0 on Feb 03, 2013
  * Initial release.
* Release 0.6.2 on Feb 04, 2013
  * Add line/column metadata on vectors, maps and symbols
* Release 0.6.4 on Feb 08, 2013
  * Fix unicode char reading
  * Add *default-data-reader-fn* support
  * Add an EDN-only reader
  * Disable record literals reading when *read-eval* is bound to false
  * Made \% a symbol constituent char
  * Made the EDN reader api match the clojure.edn one
* Release 0.6.5 on Feb 09, 2013
  * Fixed reading \@ \~ and \`

Developer Information
========================================

* [GitHub project](https://github.com/clojure/tools.reader)

* [Bug Tracker](http://dev.clojure.org/jira/browse/TRDR)

* [Continuous Integration](http://build.clojure.org/job/tools.reader/)

* [Compatibility Test Matrix](http://build.clojure.org/job/tools.reader-test-matrix/)

## License

Copyright © Nicola Mometto, Rich Hickey & contributors.

Licensed under the EPL. (See the file epl.html.)
