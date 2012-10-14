# blind

A complete clojure reader implemented in clojure itself.

## Installation

In Leiningen:

```clojure
:dependencies [[bronsa/blind "0.2.1"]]
```

## Usage

In `blind.reader` are available `read`, `read-string` and `read-line`, they have the same semantics of clojure.core ones, except they require a `blind.reader.PushbackReader`.

Two implementations of `blind.reader.PushbackReader` are provided:  `blind.reader.StringPushbackReader` and `blind.reader.IndexingPushbackReader`, functions for creating those are provided, formely: `string-push-back-reader` and `indexing-push-back-reader`.

## License

Copyright © 2012 Bronsa

Distributed under the Eclipse Public License, the same as Clojure.
