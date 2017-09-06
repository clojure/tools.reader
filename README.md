clojure.tools.reader
========================================

A complete Clojure reader and an EDN-only reader, works with Clojure versions >= 1.4.0 and Clojurescript  >=0.5308 and since version 0.10.0-alpha1

* [Rationale](#rationale)
* [Releases and Dependency Information](#releases-and-dependency-information)
* [Changelog](#changelog)
* [API Index](#api-index)
* [Developer Information](#developer-information)
* [Example Usage](#example-usage)
* [Differences from LispReader.java](#differences-from-lispreaderjava)
* [License](#license)

Rationale
========================================

clojure.tools.reader offers all functionality of the reader from clojure-1.9.0, and more.

For a list of additional features of the reader, read [Differences from LispReader.java](#differences-from-lispreaderjava)

Moreover, by using reader types from `clojure.tools.reader.reader-types`, if using an IndexingReader, column info is available and both line and column metadata is attached not only to lists, but to symbols, vectors and maps too, when additional debugging info is needed (note that the edn reader doesn't add any line/column metadata at all).


YourKit
========================================
YourKit has given an open source license for their profiler, greatly simplifying the profiling of tools.reader performance.

YourKit is kindly supporting open source projects with its full-featured Java Profiler. YourKit, LLC is the creator of innovative and intelligent tools for profiling Java and .NET applications. Take a look at YourKit's leading software products:

* <a href="http://www.yourkit.com/java/profiler/index.jsp">YourKit Java Profiler</a> and
* <a href="http://www.yourkit.com/.net/profiler/index.jsp">YourKit .NET Profiler</a>.

Releases and Dependency Information
========================================

Latest stable release: 1.0.5

* [All Released Versions](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22tools.reader%22)

* [Development Snapshot Versions](https://oss.sonatype.org/index.html#nexus-search;gav%7Eorg.clojure%7Etools.reader%7E%7E%7E)

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clojure
[org.clojure/tools.reader "1.0.5"]
```
[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>tools.reader</artifactId>
  <version>1.0.5</version>
</dependency>
```

[Changelog](CHANGELOG.md)
========================================

API Index
========================================

* [CrossClj Documentation](http://crossclj.info/doc/org.clojure/tools.reader/lastest/index.html)
* [API index](http://clojure.github.io/tools.reader)

Developer Information
========================================

* [GitHub project](https://github.com/clojure/tools.reader)

* [Bug Tracker](http://dev.clojure.org/jira/browse/TRDR)

* [Continuous Integration](http://build.clojure.org/job/tools.reader/)

* [Compatibility Test Matrix](http://build.clojure.org/job/tools.reader-test-matrix/)

Example Usage
========================================

To read data structures, functions from `clojure.tools.reader.edn` should be used, since those are **safe** and don't allow any code execution at all.

Remember that when using `read` you *need* to use a reader that implements `IPushbackReader` such as `string-push-back-reader`.

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

To switch from using `clojure.core/read-string` to `clojure.tools.reader.edn/read-string` in your projects, put this in your namespace declaration:
```clojure
(:refer-clojure :exclude [read read-string])
(:use [clojure.tools.reader.edn :only [read read-string]])
```

If (and only if) reading from a **trusted** source, and advanced features that need some level of code-execution during read are needed, functions from `clojure.tools.reader` should be used.
```clojure
(require '[clojure.tools.reader :as r])
;=> nil
(r/read-string "1")
;=> 1
;; WARNING!
(r/read-string "#=(+ 1 2)")
;=> 3
(binding [r/*read-eval* false]
  (r/read-string "#=(+ 1 2)"))
=> ExceptionInfo #= not allowed when *read-eval* is false
```

To switch from using `clojure.core/read-string` to `clojure.tools.reader/read-string` in your projects, put this in your namespace declaration:
```clojure
(:refer-clojure :exclude [read read-string *default-data-reader-fn* *read-eval* *data-readers*])
(:use [clojure.tools.reader :only [read read-string *default-data-reader-fn* *read-eval* *data-readers*]])
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
* `read` is capable of reading literal tags containing periods, fixing [#CLJ-1100](http://dev.clojure.org/jira/browse/CLJ-1100)
* `clojure.tools.reader/read` checks if `clojure.tools.reader/*alias-map*` is bound, if that's the case, aliases will be resolved by querying it (must be a map), otherwhise (ns-aliases \*ns\*) will be used
* `clojure.tools.reader/read` adds additional line/column info to symbols, vectors and maps when possible
* `clojure.tools.reader.reader-types/read-line` has an additional arity with which is possible to specify the reader to read from

## License

Copyright © 2013-2017 Nicola Mometto, Rich Hickey & contributors.

Licensed under the EPL. (See the file epl.html.)
