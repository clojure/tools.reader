clojure.tools.reader
========================================

A complete Clojure reader and an EDN-only reader, works with Clojure versions >= 1.3.0

Rationale
========================================

clojure.tools.reader offers all functionality of the reader from the upcoming clojure-1.5.0, and more.

This means safer read/read-string, an edn-only reader, tagged-literals support, default-data-reader-fn support for every Clojure version >=1.3.0

For a list of additional features of the reader, read the "Differences from LispReader.java" section from the README.

Moreover, by using reader types from `clojure.tools.reader.reader-types`, if using an IndexingReader, column info is available and both line and column metadata is attached not only to lists, but to symbols, vectors and maps too, when additional debugging info is needed (note that the edn reader doesn't add any line/column metadata at all).

Note that it uses `ex-info` which is available on `clojure.core` only from clojure-1.4.0.
If using clojure-1.3.0 and needing access to ex-data, use `clojure.tools.reader.impl.utils/ex-data`

Public API
========================================

There are three public namespaces:
* `clojure.tools.reader.reader-types` offers protocols, implementations and convenient constructors for some reader types.
* `clojure.tools.reader.edn` offers a feature-complete EDN reader, whose API matches clojure.edn's one, those functions are *safe*.
* `clojure.tools.reader` offers a feature-complete clojure reader, whose API matches clojure.core's one, those functions are *unsafe* and may allow code execution if not used properly.
* `clojure.tools.reader.default-data-readers` offers implementations for the #inst and #uuid tagged literals, copied from the clojure source.

Refer to docstrings in each namespace for more documentation.

Releases and Dependency Information
========================================

Latest stable release: 0.7.0

* [All Released Versions](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22tools.reader%22)

* [Development Snapshot Versions](https://oss.sonatype.org/index.html#nexus-search;gav%7Eorg.clojure%7Etools.reader%7E%7E%7E)

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clojure
[org.clojure/tools.reader "0.7.0"]
```
[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>tools.reader</artifactId>
  <version>0.7.0</version>
</dependency>
```

Example Usage
========================================

To read data structures, functions from `clojure.tools.reader.edn` should be used, since those are **safe** and don't allow any code execution at all.

Note that since no code-execution is permitted, reader literals are also disabled.

```clojure
(require '[clojure.tools.reader.edn :as edn])
;=> nil
(edn/read-string "1")
;=> 1
(edn/read-string "#inst \"2010-11-12T13:14:15.666\"")
;=> #inst "2010-11-12T13:14:15.666-00:00"
(let [my-unknown (fn [tag val] {:unknown-tag tag :value val})]
   (edn/read-string {:default my-unknown} "#foo bar"))
;=> {:unknown-tag foo, :value bar}
(edn/read-string {:readers {'foo (constantly 1)}} "#foo bar")
;=> 1
```

To switch from using `clojure.core/read-string` to `clojure.tools.reader.end/read-string` in your projects, put this in your namespace declaration:
```clojure
(:refer-clojure :exclude [read read-string])
(:use [clojure.tools.reader.edn [read read-string]])
```

If (and only if) reading from a **trusted** source, and advanced features that need some level of code-execution during read are needed, functions from `clojure.tools.reader` should be used.
```clojure
(require '[clojure.tools.reader.edn :as r])
;=> nil
(r/read-string "1")
;=> 1
;; WARNING!
(r/read-string "#=(+ 1 2)")
;=> 3
(binding [r/*read-eval* false]
  (r/read-string "#=(+ 1 2)))
=> ExceptionInfo #= not allowed when *read-eval* is false
```

To switch from using `clojure.core/read-string` to `clojure.tools.reader/read-string` in your projects, put this in your namespace declaration:
```clojure
(:refer-clojure :exclude [read read-string *default-data-reader-fn* *read-eval* *data-readers*])
(:use [clojure.tools.reader [read read-string *default-data-reader-fn* *read-eval* *data-readers*]])
```

Reader types example usage:
```clojure
(require '[clojure.tools.reader.reader-types :as t])
;=> nil
(def reader (t/string-push-back-reader "1"))
;=> #'user/reader
(t/read-char reader)
;=> \1
(t/unread reader \a)
;=> \a
(t/peek-char reader)
;=> \a
(t/read-char reader)
;=> \a
(t/read-char reader)
;=> nil
```
Note that the pushback buffer is of dimension 1 by default, and an exception will be thrown if trying to
unread more chars than the pushback buffer dimension.

Every predefined reader type has an additional arity that allows to specify the pushback buffer dimension.

```clojure
(def reader (t/string-push-back-reader "" 2))
;=> nil
(t/unread reader \a)
;=> \a
(t/unread reader \b)
;=> \b
(t/read-char reader)
;=> \b
(t/read-char reader)
;=> \a
(t/read-char reader)
;=> nil
```

Differences from LispReader.java
========================================

There are small differences from clojure.lang.LispReader:

* `read` throws an `ex-info` for almost every exception, whereas `clojure.lang.LispReader/read` throws a `ReaderException` wrapping the causing exception.
* `read` is capable of reading `\x` escaped chars
* `read` is capable of reading `Infinity` `+Infinity` `-Infinity` and `NaN` as per #CLJ-1074
* `read` is capable of reading literal tags containing periods, fixing #CLJ-1100
* `clojure.tools.reader/read` adds additional line/column info to symbols, vectors and maps when possible
* `read-line` has an additional arity with which is possible to specify the reader to read from

Changelog
========================================

* Release 0.6.0 on Feb 03, 2013
  * Initial release.
* Release 0.6.2 on Feb 04, 2013
  * Add line/column metadata on vectors, maps and symbols
* Release 0.6.4 on Feb 08, 2013
  * Fix Unicode char reading
  * Add \*default-data-reader-fn\* support
  * Add an EDN-only reader
  * Disable record literals reading when \*read-eval\* is bound to false
  * Made \% a symbol constituent char
  * Made the EDN reader API match the clojure.edn one
* Release 0.6.5 on Feb 09, 2013
  * Fixed reading \@ \~ and \`
* Release 0.7.0 on Feb 14, 2013
  * Fixed #TRDR-1 by @jafingerhut
  * Made compatible with clojure-1.3.0
  * Decoupled from clojure.core vars (\*read-eval\*, \*default-data-reader-fn\*, \*data-readers\*)
  * clojure.tools.reader/read-string and clojure.tools.reader.edn/read-string return nil if string is nil or empty
  * Added comprehensive docstrings
* Release 0.7.1 on ???
  * Added the syntax-quote macro to the public API

Developer Information
========================================

* [GitHub project](https://github.com/clojure/tools.reader)

* [Bug Tracker](http://dev.clojure.org/jira/browse/TRDR)

* [Continuous Integration](http://build.clojure.org/job/tools.reader/)

* [Compatibility Test Matrix](http://build.clojure.org/job/tools.reader-test-matrix/)

## License

Copyright © Nicola Mometto, Rich Hickey & contributors.

Licensed under the EPL. (See the file epl.html.)
