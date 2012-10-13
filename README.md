# blind

A complete clojure reader implemented in clojure itself.
Not yet up to date with the new column info added recently.


## Installation

In Leiningen:

```clojure
:dependencies [[bronsa/blind "0.1.0"]]
```

## Usage

In `blind.reader` are available `read`, `read-string` and `read-line`, they have the same semantics of clojure.core ones, except they require a `blind.reader.PushbackReader`.

Two implementations of `blind.reader.PushbackReader` are provided:  `blind.reader.StringPushbackReader` and `blind.reader.LineNumberingPushbackReader`.

## License

Copyright © 2012 Bronsa

Distributed under the Eclipse Public License, the same as Clojure.
