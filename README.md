# blind

A complete clojure reader implemented in clojure itself.

## Installation

In Leiningen:

```clojure
:dependencies [[bronsa/blind "0.3.4"]]
```

## NOTE

On my machine it reads core.clj 1.5x times slower than clojure's reader

## Usage

In `blind.reader` are available `read`, `read-string` and `read-line`, they have the same semantics of clojure.core ones.

`read-string` uses internally a blind.reader.StringReader with fast peek-char semantics.

`read` and `read-line` work as their clojure.core counterparts, requiring a `blind.reader.IPushbackReader` (Read the end of usage for java.io.PushbackReader)

`blind.reader` also provides `string-reader`, `input-stream-reader`, `string-push-back-reader`, `input-stream-push-back-reader` and `indexing-push-back-reader`.

`indexing-push-back-reader` takes a string or a `blind.reader.IPushbackreader` and an optional pushback buffer size and returns a blind.reader.IndexingPushbackReader that can be used with `read` to get column/line infos.

Note that `blind.reader.Reader` and `blind.reader.PushbackReader` have been extended to `java.io.PushbackReader` so both `java.io.PushbackReader` and `clojure.lang.LineNumberingPushbackReader` work as `blind.reader.PushbackReader`s

## Differences from clojure's reader

There are small differences from clojure's LispReader.java:

`blind.reader/read` throws an `ex-info` for almost every exception, whereas `clojure.lang.LispReader/read` throws a `ReaderException` wrapping the causing exception.

`blind.reader/read` is capable of reading `\x` escaped chars

`blind.reader/read` is capable of reading `Infinity` `+Infinity` `-Infinity` and `NaN` as per #CLJ-1074

`blind.reader/read-line` has an additional arity with which is possible to specify the reader to read from

## License

Copyright © 2012 Bronsa

Distributed under the Eclipse Public License, the same as Clojure.
