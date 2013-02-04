clojure.tools.reader
========================================

A complete Clojure reader implemented in Clojure.

Releases and Dependency Information
========================================

Latest stable release: 0.6.2

* [All Released Versions](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22tools.reader%22)

* [Development Snapshot Versions](https://oss.sonatype.org/index.html#nexus-search;gav%7Eorg.clojure%7Etools.reader%7E%7E%7E)

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clojure
[org.clojure/tools.reader "0.6.2"]
```
[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>tools.reader</artifactId>
  <version>0.6.2</version>
</dependency>
```
Example Usage
========================================

```clojure
(require '[clojure.tools.reader :as t.r])

(def reader (t.r/string-push-back-reader "1"))

(t.r/read-char reader) ;=> \1

(t.r/unread reader \1) ;=> \1

(t.r/read reader) ;=> 1

(t.r/read-string "2") ;=> 2
```

Differences from LispReader.java
========================================

There are small differences from clojure.lang.LispReader:

* `t.r/read` throws an `ex-info` for almost every exception, whereas `clojure.lang.LispReader/read` throws a `ReaderException` wrapping the causing exception.

* `t.r/read` is capable of reading `\x` escaped chars

* `t.r/read` is capable of reading `Infinity` `+Infinity` `-Infinity` and `NaN` as per #CLJ-1074

* `t.r/read` is capable of reading literal tags contaning periods, fixing #CLJ-1100

* `t.r/read` checks if `t.r/*alias-map*` is bound, if that's the case, aliases will be resolved by querying it (must be a map), otherwhise (ns-aliases *ns*) will be used

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


Developer Information
========================================

* [GitHub project](https://github.com/clojure/tools.reader)

* [Bug Tracker](http://dev.clojure.org/jira/browse/TRDR)

* [Continuous Integration](http://build.clojure.org/job/tools.reader/)

* [Compatibility Test Matrix](http://build.clojure.org/job/tools.reader-test-matrix/)

## License

Copyright © Nicola Mometto, Rich Hickey & contributors.

Licensed under the EPL. (See the file epl.html.)
