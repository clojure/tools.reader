# blind

A complete clojure reader implemented in clojure itself.

## Installation

In Leiningen:

```clojure
:dependencies [[bronsa/blind "0.3.0"]]
```

## NOTE

On my machine it reads core.clj 1.5x times slower than clojure's reader

## Usage

In `blind.reader` are available `read`, `read-string` and `read-line`, they have the same semantics of clojure.core ones, except they require a `blind.reader.PushbackReader`.

Two implementations of `blind.reader.PushbackReader` are provided:  `blind.reader.StringPushbackReader` and `blind.reader.IndexingPushbackReader`, functions for creating those are provided, formely: `string-push-back-reader` and `indexing-push-back-reader`.

## Differences from clojure's reader

There are small differences from clojure's LispReader.java:

`blind.reader/read` throws an `ex-info` for almost every exception, whereas `clojure.lang.LispReader/read` throws a `ReaderException` wrapping the causing exception.

`blind.reader/read` is capable of reading `\x` escaped chars

`blind.reader/read` is capable of reading `Infinity` `+Infinity` `-Infinity` and `NaN` as per #CLJ-1074

## License

Copyright © 2012 Bronsa

Distributed under the Eclipse Public License, the same as Clojure.
