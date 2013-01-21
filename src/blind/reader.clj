(set! *warn-on-reflection* true)

(ns blind.reader
  (:refer-clojure :exclude [read read-line read-string char])
  (:import (clojure.lang BigInt Numbers PersistentHashMap PersistentHashSet IMeta
                         RT IReference Symbol Reflector Var IObj
                         PersistentVector IRecord Namespace LineNumberingPushbackReader)
           java.io.InputStream
           (java.util ArrayList regex.Pattern regex.Matcher)
           java.lang.reflect.Constructor))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; utils
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defmacro ^:private update! [what f]
  (list 'set! what (list f what)))

(defn- char [x]
  (try (clojure.core/char x)
       (catch NullPointerException e)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; reader protocols
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defprotocol Reader
  (read-char [reader] "Returns the next char from the Reader, nil if the end of stream has been reached")
  (peek-char [reader] "Returns the next char from the Reader without removing it from the reader stream"))

(defprotocol IPushbackReader
  (unread [reader ch] "Push back a single character on to the stream"))

(defprotocol IndexingReader
  (get-line-number [reader])
  (get-column-number [reader]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; reader deftypes
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(deftype StringReader
    [^String s s-len ^:unsynchronized-mutable s-pos]
  Reader
  (read-char [reader]
    (when (> s-len s-pos)
      (let [r (nth s s-pos)]
        (update! s-pos inc)
        r)))
  (peek-char [reader]
    (when (> s-len s-pos)
      (nth s s-pos))))

(deftype InputStreamReader [^InputStream is ^:unsynchronized-mutable ^bytes buf]
  Reader
  (read-char [reader]
    (if buf
      (let [c (aget buf 0)]
        (set! buf nil)
        (char c))
      (let [c (.read is)]
        (when (pos? c)
          (char c)))))
  (peek-char [reader]
    (when-not buf
      (set! buf (byte-array 1))
      (when (== -1 (.read is buf))
        (set! buf nil)))
    (when buf
      (char (aget buf 0)))))

(deftype PushbackReader
    [rdr ^objects buf buf-len ^:unsynchronized-mutable buf-pos]
  Reader
  (read-char [reader]
    (char
     (if (< buf-pos buf-len)
       (let [r (aget buf buf-pos)]
         (update! buf-pos inc)
         r)
       (read-char rdr))))
  (peek-char [reader]
    (char
     (if (< buf-pos buf-len)
       (aget buf buf-pos)
       (peek-char rdr))))
  IPushbackReader
  (unread [reader ch]
    (when ch
      (if (zero? buf-pos) (throw (RuntimeException. "Pushback buffer is full")))
      (update! buf-pos dec)
      (aset buf buf-pos ch))))

(declare newline?)

(defn- normalize-newline [rdr ch]
  (if (identical? \return ch)
    (let [c (peek-char rdr)]
      (when (identical? \formfeed c)
        (read-char rdr))
      \newline)
    ch))

(deftype IndexingPushbackReader
    [rdr ^:unsynchronized-mutable line ^:unsynchronized-mutable column
     ^:unsynchronized-mutable line-start? ^:unsynchronized-mutable prev]
  Reader
  (read-char [reader]
    (when-let [ch (read-char rdr)]
      (let [ch (normalize-newline rdr ch)]
        (set! prev line-start?)
        (set! line-start? (newline? ch))
        (when line-start?
          (set! column 0)
          (update! line inc))
        (update! column inc)
        ch)))

  (peek-char [reader]
    (peek-char rdr))

  IPushbackReader
  (unread [reader ch]
    (when line-start? (update! line dec))
    (set! line-start? prev)
    (update! column dec)
    (unread rdr ch))

  IndexingReader
  (get-line-number [reader] (int (inc line)))
  (get-column-number [reader]  (int column)))

(extend-type java.io.PushbackReader
  Reader
  (read-char [rdr]
    (let [c (.read ^java.io.PushbackReader rdr)]
      (when (pos? c)
        (normalize-newline rdr (char c)))))

  (peek-char [rdr]
    (when-let [c (read-char rdr)]
      (unread rdr c)
      c))

  IPushbackReader
  (unread [rdr c]
    (when c
      (.unread ^java.io.PushbackReader rdr (int c)))))

;; getColumnNumber is available only since clojure-1.5.0-beta1
;; (extend-type LineNumberingPushbackReader
;;   IndexingReader
;;   (get-line-number [rdr]
;;     (.getLineNumber ^LineNumberingPushbackReader rdr))
;;   (get-column-number [rdr]
;;     (.getColumnNumber ^LineNumberingPushbackReader rdr)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; predicates
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn- whitespace?
  "Checks whether a given character is whitespace"
  [ch]
  (when ch
    (or (Character/isWhitespace ^Character ch)
        (identical? \,  ch))))

(defn- numeric?
  "Checks whether a given character is numeric"
  [^Character ch]
  (when ch
    (Character/isDigit ch)))

(defn- comment-prefix?
  "Checks whether the character begins a comment."
  [ch]
  (identical? \;  ch))

(defn- number-literal?
  "Checks whether the reader is at the start of a number literal"
  [reader initch]
  (or (numeric? initch)
      (and (or (identical? \+ initch) (identical?  \- initch))
           (numeric? (peek-char reader)))))

(defn newline? [c]
  "Checks whether the character is a newline"
  (or (identical? \newline c)
      (nil? c)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; read helpers
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(declare read macros dispatch-macros)

(defn reader-error
  "Throws an Exception info, if rdr is an IndexingReader, additional information about column and line number is provided"
  [rdr & msg]
  (throw (ex-info (apply str msg)
                  (merge {:type :reader-exception}
                         (if (instance? blind.reader.IndexingReader rdr)
                           {:line (get-line-number rdr)
                            :column (get-column-number rdr)})))))

(defn macro-terminating? [ch]
  (and (not (identical? \# ch))
       (not (identical? \' ch))
       (not (identical? \: ch))
       (macros ch)))

(defn ^String read-token
  [rdr initch]
  (if-not initch
    (reader-error rdr "EOF while reading")
    (loop [sb (doto (StringBuilder.) (.append initch))
           ch (peek-char rdr)]
      (if (or (whitespace? ch)
              (macro-terminating? ch)
              (nil? ch))
        (str sb)
        (recur (doto sb (.append (read-char rdr))) (peek-char rdr))))))

(defn read-past
  "Read until first character that doesn't match pred, returning
   char."
  [pred rdr]
  (loop [ch (read-char rdr)]
    (if (pred ch)
      (recur (read-char rdr))
      ch)))

(defn skip-line
  "Advances the reader to the end of a line. Returns the reader"
  [reader _]
  (loop []
    (when-not (newline? (read-char reader))
      (recur)))
  reader)

(def ^Pattern int-pattern #"([-+]?)(?:(0)|([1-9][0-9]*)|0[xX]([0-9A-Fa-f]+)|0([0-7]+)|([1-9][0-9]?)[rR]([0-9A-Za-z]+)|0[0-9]+)(N)?")
(def ^Pattern ratio-pattern #"([-+]?[0-9]+)/([0-9]+)")
(def ^Pattern float-pattern #"([-+]?[0-9]+(\.[0-9]*)?([eE][-+]?[0-9]+)?)(M)?")

(defn- match-int
  [^Matcher m]
  (if (.group m 2)
    (if (.group m 8) 0N 0)
    (let [negate? (= "-" (.group m 1))
          a (cond
             (.group m 3) [(.group m 3) 10]
             (.group m 4) [(.group m 4) 16]
             (.group m 5) [(.group m 5) 8]
             (.group m 7) [(.group m 7) (Integer/parseInt (.group m 6))]
             :else        [nil nil])
          ^String n (a 0)
          ^int radix (a 1)]
      (when n
        (let [bn (BigInteger. n radix)
              bn (if negate? (.negate bn) bn)]
          (if (.group m 8)
            (BigInt/fromBigInteger bn)
            (if (< (.bitLength bn) 64)
              (.longValue bn)
              (BigInt/fromBigInteger bn))))))))

(defn- match-ratio
  [^Matcher m]
  (let [^String numerator (.group m 1)
        ^String denominator (.group m 2)]
    (/ (-> numerator   BigInteger. BigInt/fromBigInteger Numbers/reduceBigInt)
       (-> denominator BigInteger. BigInt/fromBigInteger Numbers/reduceBigInt))))

(defn- match-float
  [^String s ^Matcher m]
  (if (.group m 4)
    (BigDecimal. ^String (.group m 1))
    (Double/parseDouble s)))

(defn match-number [^String s]
  (let [int-matcher (.matcher int-pattern s)]
    (if (.matches int-matcher)
      (match-int int-matcher)
      (let [float-matcher (.matcher float-pattern s)]
        (if (.matches float-matcher)
          (match-float s float-matcher)
          (let [ratio-matcher (.matcher ratio-pattern s)]
            (when (.matches ratio-matcher)
              (match-ratio ratio-matcher))))))))

(defn- parse-symbol [^String token]
  (when-not (= "" token)
    (let [ns-idx (inc (.indexOf token "/"))]
      (if-let [ns (and (pos? ns-idx) (subs token 0 (dec ns-idx)))]
        (when-not (== ns-idx (count token))
          (let [sym (subs token ns-idx)]
            (when (and (not (numeric? (nth sym 0)))
                       (not (= "" sym))
                       (or (= sym "/")
                           (== -1 (.indexOf sym "/"))))
              [ns sym])))
        [nil token]))))

(declare read-tagged)

(defn read-dispatch
  [rdr _]
  (if-let [ch (read-char rdr)]
    (if-let [dm (dispatch-macros ch)]
      (dm rdr ch)
      (if-let [obj (read-tagged (doto rdr (unread ch)) ch)] ;; ctor reader is implemented as a taggged literal
        obj
        (reader-error rdr "No dispatch macro for " ch)))
    (reader-error rdr "EOF while reading character")))

(defn read-unmatched-delimiter
  [rdr ch]
  (reader-error rdr "Unmatched delimiter " ch))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; readers
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(def read-comment skip-line)

(defn read-unicode-char
  ([^String token offset length base]
     (let [l (+ offset length)]
       (when-not (== (count token) l)
         (throw (IllegalArgumentException. (str "Invalid unicode character: \\" token))))
       (loop [i offset uc 0]
         (if (== i l)
           (char uc)
           (let [d (Character/digit ^char (nth token i) ^int base)]
             (if (== d -1)
               (throw (IllegalArgumentException. (str "Invalid digit: " (nth token i))))
               (recur (inc i) (long (+ d (* uc base))))))))))

  ([rdr initch base length exact?]
     (loop [i 1 uc (Character/digit ^char initch ^int base)]
       (if (== uc -1)
         (throw (IllegalArgumentException. (str "Invalid digit: " initch)))
         (if-not (== i length)
           (let [ch (peek-char rdr)]
             (if (or (whitespace? ch)
                     (macros ch)
                     (nil? ch))
               (if exact?
                 (throw (IllegalArgumentException.
                         (str "Invalid character length: " i ", should be: " length)))
                 (char uc))
               (let [d (Character/digit ^char ch ^int base)]
                 (read-char rdr)
                 (if (== d -1)
                   (throw (IllegalArgumentException. (str "Invalid digit: " ch)))
                   (recur (inc i) (long (+ d (* uc base))))))))
           (char uc))))))

(let [upper-limit (int \uD799)
      lower-limit (int \uE000)]
  (defn read-char*
    [rdr backslash]
    (let [ch (read-char rdr)]
      (if-not (nil? ch)
        (let [token (read-token rdr ch)
              token-len (count token)]
          (cond

           (== 1 token-len)  (Character/valueOf (nth token 0))

           (= token "newline") \newline
           (= token "space") \space
           (= token "tab") \tab
           (= token "backspace") \backspace
           (= token "formfeed") \formfeed
           (= token "return") \return

           (.startsWith token "u")
           (let [c (read-unicode-char token 1 4 16)
                 ic (int c)]
             (if (and (> ic upper-limit)
                      (< ic lower-limit))
               (reader-error rdr "Invalid character constant: \\u" (Integer/toString ic 16))
               c))

           (.startsWith token "x")
           (read-unicode-char token 1 2 16)

           (.startsWith token "o")
           (let [len (dec token-len)]
             (if (> len 3)
               (reader-error rdr "Invalid octal escape sequence length: " len)
               (let [uc (read-unicode-char token 1 len 8)]
                 (if (> (int uc) 0377)
                   (reader-error rdr "Octal escape sequence must be in range [0, 377]")
                   uc))))

           :else (reader-error rdr "Unsupported character: \\" token)))
        (reader-error rdr "EOF while reading character")))))

(defn ^PersistentVector read-delimited-list
  [delim rdr recursive?]
  (let [first-line  (when (instance? blind.reader.IndexingReader rdr)
                      (get-line-number rdr))
        delim ^char delim]
    (loop [a (transient [])]
      (let [ch (read-past whitespace? rdr)]
        (when-not ch
          (reader-error rdr "EOF while reading"
                        (if first-line
                          (str ", starting at line" first-line))))
        (if (identical? delim ^char ch)
          (persistent! a)
          (if-let [macrofn (macros ch)]
            (let [mret (macrofn rdr ch)]
              (recur (if-not (identical? mret rdr) (conj! a mret) a)))
            (let [o (read (doto rdr (unread ch)) true nil recursive?)]
              (recur (if-not (identical? o rdr) (conj! a o) a)))))))))

(defn read-list
  [rdr _]
  (let [[line column] (when (instance? blind.reader.IndexingReader rdr)
                        [(get-line-number rdr) (dec (get-column-number rdr))])
        the-list (read-delimited-list \) rdr true)]
    (if (empty? the-list)
      '()
      (if-not line
        (clojure.lang.PersistentList/create the-list)
        (with-meta (clojure.lang.PersistentList/create the-list) {:line line :column column})))))

(defn read-vector
  [rdr _]
  (read-delimited-list \] rdr true))

(defn read-map
  [rdr _]
  (let [l (to-array (read-delimited-list \} rdr true))]
    (when (== 1 (bit-and (alength l) 1))
      (reader-error rdr "Map literal must contain an even number of forms"))
    (RT/map l)))

(defn read-number
  [reader initch]
  (loop [sb (doto (StringBuilder.) (.append initch))
         ch (read-char reader)]
    (if (or (whitespace? ch) (macros ch) (nil? ch))
      (let [s (str sb)]
        (unread reader ch)
        (or (match-number s)
            (reader-error reader "Invalid number format [" s "]")))
      (recur (doto sb (.append ch)) (read-char reader)))))

(defn escape-char [sb rdr]
  (let [ch (read-char rdr)]
    (case ch
      \t "\t"
      \r "\r"
      \n "\n"
      \\ "\\"
      \" "\""
      \b "\b"
      \f "\f"
      \u (let [ch (read-char rdr)]
           (if (== -1 (Character/digit ^char ch 16))
             (reader-error rdr "Invalid unicode escape: \\u" ch)
             (read-unicode-char rdr ch 16 4 true)))
      \x (let [ch (read-char rdr)]
           (if (== -1 (Character/digit ^char ch 16))
             (reader-error rdr "Invalid unicode escape: \\x" ch)
             (read-unicode-char rdr ch 16 2 true)))
      (if (numeric? ch)
        (let [ch (read-unicode-char rdr ch 8 3 false)]
          (if (> (int ch) 0337)
            (reader-error rdr "Octal escape sequence must be in range [0, 377]")
            ch))
        (reader-error rdr "Unsupported escape character: \\" ch)))))

(defn read-string*
  [reader _]
  (loop [sb (StringBuilder.)
         ch (read-char reader)]
    (case ch
      nil (reader-error reader "EOF while reading string")
      \\ (recur (doto sb (.append (escape-char sb reader)))
                (read-char reader))
      \" (str sb)
      (recur (doto sb (.append ch)) (read-char reader)))))

(defn read-symbol
  [rdr initch]
  (when-let [token (read-token rdr initch)]
    (case token

      ;; special symbols
      "nil" nil
      "true" true
      "false" false
      "/" '/
      "NaN" Double/NaN
      "-Infinity" Double/NEGATIVE_INFINITY
      ("Infinity" "+Infinity") Double/POSITIVE_INFINITY

      (or (when-let [p (parse-symbol token)]
            (symbol (p 0) (p 1)))
          (reader-error rdr "Invalid token: " token)))))

(def ^:dynamic *alias-map* nil)
(defn- resolve-ns [sym]
  (or ((or *alias-map*
           (ns-aliases *ns*)) sym)
      (find-ns sym)))

(defn read-keyword
  [reader initch]
  (let [ch (read-char reader)]
    (if-not (whitespace? ch)
      (let [token (read-token reader ch)
            s (parse-symbol token)]
        (if (and s (== -1 (.indexOf token "::")))
          (let [^String ns (s 0)
                ^String name (s 1)]
            (if (identical? \: (nth token 0))
              (if ns
                (let [ns (resolve-ns (symbol (subs ns 1)))]
                  (if ns
                    (keyword (str ns) name)
                    (reader-error reader "Invalid token: :" token)))
                (keyword (str *ns*) (subs name 1)))
              (keyword ns name)))
          (reader-error reader "Invalid token: :" token)))
      (reader-error reader "Invalid token: :"))))

(defn desugar-meta
  [f]
  (cond
   (symbol? f) {:tag f}
   (string? f) {:tag f}
   (keyword? f) {f true}
   :else f))

(defn wrapping-reader
  [sym]
  (fn [rdr _]
    (list sym (read rdr true nil true))))

(defn throwing-reader
  [msg]
  (fn [rdr _]
    (reader-error rdr msg)))

(defn read-meta
  [rdr _]
  (let [[line column] (when (instance? blind.reader.IndexingReader rdr)
                        [(get-line-number rdr) (dec (get-column-number rdr))])
        m (desugar-meta (read rdr true nil true))]
    (when-not (map? m)
      (reader-error rdr "Metadata must be Symbol, Keyword, String or Map"))
    (let [o (read rdr true nil true)]
      (if (instance? IMeta o)
        (let [m (if (and line
                         (seq? o))
                  (assoc m :line line
                           :column column)
                  m)]
          (if (instance? IObj o)
            (with-meta o (merge (meta o) m))
            (reset-meta! o m)))
        (reader-error rdr "Metadata can only be applied to IMetas")))))

(defn read-set
  [rdr _]
  (PersistentHashSet/createWithCheck (read-delimited-list \} rdr true)))

(defn read-regex
  [rdr ch]
  (let [sb (StringBuilder.)]
    (loop [ch (read-char rdr)]
      (if (identical? \" ch)
        (Pattern/compile (str sb))
        (if (nil? ch)
          (reader-error rdr "EOF while reading regex")
          (do
            (.append sb ch )
            (when (identical? \\ ch)
              (let [ch (read-char rdr)]
                (if (nil? ch)
                  (reader-error rdr "EOF while reading regex"))
                (.append sb ch)))
            (recur (read-char rdr))))))))

(defn read-discard
  [rdr _]
  (read rdr true nil true)
  rdr)

(def ^:private ^:dynamic arg-env nil)

(defn- garg [n]
  (symbol (str (if (== -1 n) "rest" (str "p" n))
               "__" (RT/nextID) "#")))

(defn read-fn
  [rdr _]
  (if arg-env
    (throw (IllegalStateException. "Nested #()s are not allowed")))
  (with-bindings {#'arg-env (sorted-map)}
    (unread rdr \()
    (let [form (read rdr true nil true) ;; this sets bindings
          rargs (rseq arg-env)
          args (if rargs
                 (let [higharg (key (first rargs))]
                   (if (pos? higharg)
                     (let [args (loop [i 1 args (transient [])]
                                  (if (> i higharg)
                                    (persistent! args)
                                    (recur (inc i) (conj! args (or (get arg-env i)
                                                                   (garg i))))))
                           args (if (arg-env -1)
                                  (conj args '& (arg-env -1))
                                  args)]
                       args)))
                 [])]
      (list 'fn* args form))))

(defn register-arg [n]
  (if arg-env
    (if-let [ret (arg-env n)]
      ret
      (let [g (garg n)]
        (set! arg-env (assoc arg-env n g))
        g))
    (throw (IllegalStateException. "Arg literal not in #()")))) ;; should never hit this

(declare read-symbol)

(defn read-arg
  [rdr pct]
  (if-not arg-env
    (read-symbol rdr pct)
    (let [ch (peek-char rdr)]
      (if (or (whitespace? ch)
              (macro-terminating? ch)
              (nil? ch))
        (register-arg 1)
        (let [n (read rdr true nil true)]
          (if (= n '&)
            (register-arg -1)
            (if-not (number? n)
              (throw (IllegalStateException. "Arg literal must be %, %& or %integer"))
              (register-arg n))))))))

(defn read-eval
  [rdr _]
  (when-not *read-eval*
    (reader-error rdr "#= not allowed when *read-eval* is false"))
  (let [o (read rdr true nil true)]
    (if (symbol? o)
      (RT/classForName (str ^Symbol o))
      (if (list? o)
        (let [fs (first o)
              o (rest o)
              fs-name (name fs)]
          (cond
           (= fs 'var) (let [vs (first o)]
                         (RT/var (namespace vs) (name vs)))
           (.endsWith fs-name ".")
           (let [args (to-array o)]
             (-> fs-name (subs 0 (dec (count fs-name)))
                 RT/classForName (Reflector/invokeConstructor args)))

           (Compiler/namesStaticMember fs)
           (let [args (to-array o)]
             (Reflector/invokeStaticMethod (namespace fs) fs-name args))

           :else
           (let [v (Compiler/maybeResolveIn *ns* fs)]
             (if (var? v)
               (apply v o)
               (reader-error rdr "Can't resolve " fs)))))
        (throw (IllegalArgumentException. "Unsupported #= form"))))))

(def ^:private ^:dynamic gensym-env nil)

(defn read-unquote
  [rdr comma]
  (if-let [ch (peek-char rdr)]
    (if (identical? \@ ch)
      ((wrapping-reader 'clojure.core/unquote-splicing) (doto rdr read-char) \@)
      ((wrapping-reader 'clojure.core/unquote) rdr \~))))

(declare syntax-quote)
(defn unquote-splicing? [form]
  (and (seq? form)
       (= (first form) 'clojure.core/unquote-splicing)))

(defn unquote? [form]
  (and (seq? form)
       (= (first form) 'clojure.core/unquote)))

(defn- expand-list [s]
  (loop [s (seq s) r (transient [])]
    (if s
      (let [item (first s)
            ret (conj! r
                       (cond
                        (unquote? item)          (list 'clojure.core/list (second item))
                        (unquote-splicing? item) (second item)
                        :else                    (list 'clojure.core/list (syntax-quote item))))]
        (recur (next s) ret))
      (seq (persistent! r)))))

(defn- flatten-map [form]
  (loop [s (seq form) key-vals (transient [])]
    (if s
      (let [e (first s)]
        (recur (next s) (-> key-vals
                            (conj! (key e))
                            (conj! (val e)))))
      (seq (persistent! key-vals)))))

(defn- register-gensym [sym]
  (if-not gensym-env
    (throw (IllegalStateException. "Gensym literal not in syntax-quote")))
  (or (get gensym-env sym)
      (let [gs (symbol (str (subs (name sym)
                                  0 (dec (count (name sym))))
                            "__" (RT/nextID) "__auto__"))]
        (set! gensym-env (assoc gensym-env sym gs))
        gs)))

(defn- resolve-symbol [s]
  (if (pos? (.indexOf (name s) "."))
    s
    (if-let [ns-str (namespace s)]
      (let [^Namespace ns (resolve-ns (symbol ns-str))]
        (if (or (nil? ns)
                (= (name (.name ns)) ns-str)) ;; not an alias
          s
          (symbol (name (.name ns)) (name s))))
      (if-let [o ((ns-map *ns*) s)]
        (if (class? o)
          (symbol (.getName ^Class o))
          (if (var? o)
            (symbol (-> ^Var o .ns .name name) (-> ^Var o .sym name))))
        (symbol (name (.name *ns*)) (name s))))))

(defn- add-meta [form ret]
  (if (and (instance? IObj form)
           (dissoc (meta form) :line :column))
    (list 'clojure.core/with-meta ret (syntax-quote (meta form)))
    ret))

(defn- syntax-quote-coll [type coll]
  (let [res (list 'clojure.core/seq
                  (cons 'clojure.core/concat
                        (expand-list coll)))]
    (if type
      (list 'clojure.core/apply type res)
      res)))

(defn syntax-quote [form]
  (->>
   (cond
    (.containsKey Compiler/specials form) (list 'quote form)

    (symbol? form)
    (list 'quote
          (if (namespace form)
            (let [maybe-class ((ns-map *ns*)
                               (symbol (namespace form)))]
              (if (class? class)
                (symbol (.getName ^Class maybe-class) (name form))
                (resolve-symbol form)))
            (let [sym (name form)]
              (cond
               (.endsWith sym "#")
               (register-gensym form)

               (.startsWith sym ".")
               form

               (.endsWith sym ".")
               (let [csym (symbol (subs sym 0 (dec (count sym))))]
                 (symbol (.concat (name (resolve-symbol csym)) ".")))
               :else (resolve-symbol form)))))

    (unquote? form) (second form)
    (unquote-splicing? form) (throw (IllegalStateException. "splice not in list"))

    (coll? form)
    (cond
     (instance? IRecord form) form
     (map? form) (syntax-quote-coll 'clojure.core/hash-map (flatten-map form))
     (vector? form) (syntax-quote-coll 'clojure.core/vector form)
     (set? form) (syntax-quote-coll 'clojure.core/hash-set form)
     (or (seq? form) (list? form))
     (let [seq (seq form)]
       (if seq
         (syntax-quote-coll nil seq)
         '(clojure.core/list)))
     :else (throw (UnsupportedOperationException. "Unknown Collection type")))

    (or (keyword? form)
        (number? form)
        (char? form)
        (string? form))
    form

    :else (list 'quote form))
   (add-meta form)))

(defn read-syntax-quote
  [rdr backquote]
  (with-bindings {#'gensym-env {}}
    (-> (read rdr true nil true)
        syntax-quote)))

(defn macros [ch]
  (case ch
    \" read-string*
    \: read-keyword
    \; read-comment
    \' (wrapping-reader 'quote)
    \@ (wrapping-reader 'clojure.core/deref)
    \^ read-meta
    \` read-syntax-quote ;;(wrapping-reader 'syntax-quote)
    \~ read-unquote
    \( read-list
    \) read-unmatched-delimiter
    \[ read-vector
    \] read-unmatched-delimiter
    \{ read-map
    \} read-unmatched-delimiter
    \\ read-char*
    \% read-arg
    \# read-dispatch
    nil))

(defn dispatch-macros [ch]
  (case ch
    \^ read-meta                ;deprecated
    \' (wrapping-reader 'var)
    \( read-fn
    \= read-eval
    \{ read-set
    \< (throwing-reader "Unreadable form")
    \" read-regex
    \! read-comment
    \_ read-discard
    nil))

(defn read-tagged* [rdr tag f]
  (let [o (read rdr true nil true)]
    (f o)))

(defn read-ctor [rdr class-name]
  (let [class (RT/classForName (name class-name))
        ch (read-past whitespace? rdr)] ;; differs from clojure
    (if-let [[end-ch form] (case ch
                             \[ [\] :short]
                             \{ [\} :extended]
                             nil)]
      (let [entries (to-array (read-delimited-list end-ch rdr true))
            all-ctors (.getConstructors class)
            ctors-num (count all-ctors)]
        (case form
          :short
          (loop [i 0]
            (if (> i ctors-num)
              (reader-error rdr "Unexpected number of constructor arguments to " (str class)
                            ": got" (count entries))
              (if (== (count (.getParameterTypes ^Constructor (aget all-ctors i)))
                      ctors-num)
                (Reflector/invokeConstructor class entries)
                (recur (inc i)))))
          :extended
          (let [vals (RT/map entries)]
            (loop [s (keys vals)]
              (if s
                (if-not (keyword? (first s))
                  (reader-error rdr "Unreadable ctor form: key must be of type clojure.lang.Keyword")
                  (recur (next s)))))
            (Reflector/invokeStaticMethod class "create" (object-array [vals])))))
      (reader-error rdr "Invalid reader constructor form"))))

(defn read-tagged [rdr initch]
  (let [tag (read rdr true nil false)]
    (if-not (symbol? tag)
      (reader-error rdr "Reader tag must be a symbol"))
    (if-let [f (or (*data-readers* tag)
                   (default-data-readers tag))]
      (read-tagged* rdr tag f)
      (if (.contains (name tag) ".")
        (read-ctor rdr tag)
        (reader-error rdr "No reader function for tag " (name tag))))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Public API
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn string-reader
  "Creates a StringReader from a given string"
  ([^String s]
     (StringReader. s (count s) 0)))

(defn string-push-back-reader
  "Creates a PushbackReader from a given string"
  ([s]
     (string-push-back-reader s 1))
  ([^String s buf-len]
     (PushbackReader. (string-reader s) (object-array buf-len) buf-len buf-len)))

(defn input-stream-reader
  "Creates an InputStreamReader from an InputString"
  [is]
  (InputStreamReader. is nil))

(defn input-stream-push-back-reader
  "Creates a PushbackReader from a given InputString"
  ([is]
     (input-stream-push-back-reader is 1))
  ([^InputStream is buf-len]
     (PushbackReader. (input-stream-reader is) (object-array buf-len) buf-len buf-len)))

(defn indexing-push-back-reader
  "Creates an IndexingPushbackReader from a given string or Reader"
  ([s-or-rdr]
     (IndexingPushbackReader.
      ((if (string? s-or-rdr) string-push-back-reader input-stream-push-back-reader)
       s-or-rdr) 0 1 true nil))
  ([s-or-rdr buf-len]
     (IndexingPushbackReader.
      ((if (string? s-or-rdr) string-push-back-reader input-stream-push-back-reader)
       s-or-rdr buf-len) 0 1 true nil)))

(defn read
  "Reads the first object from an IPushbackReader or a java.io.PushbackReader.
Returns the object read. If EOF, throws if eof-error? is true. Otherwise returns sentinel."
  ([] (read *in*))
  ([reader] (read reader true nil))
  ([reader eof-error? sentinel] (read reader eof-error? sentinel false))
  ([^blind.reader.IPushbackReader reader eof-error? sentinel recursive?]
     (try
       (let [ch (read-char reader)]
         (cond
          (whitespace? ch) (read reader eof-error? sentinel recursive?)
          (nil? ch) (if eof-error? (reader-error reader "EOF") sentinel)
          (number-literal? reader ch) (read-number reader ch)
          (comment-prefix? ch) (read (read-comment reader ch) eof-error? sentinel recursive?)
          :else (let [f (macros ch)]
                  (if f
                    (let [res (f reader ch)]
                      (if (identical? res reader)
                        (read reader eof-error? sentinel recursive?)
                        res))
                    (read-symbol reader ch)))))
       (catch Exception e
         (if (instance? clojure.lang.ExceptionInfo e)
           (throw e)
           (throw (ex-info (.getMessage e)
                           (merge {:type :reader-exception}
                                  (if (instance? blind.reader.IndexingReader reader)
                                    {:line (get-line-number reader)
                                     :column (get-column-number reader)}))
                           e)))))))

(defn read-string
  "Reads one object from the string s"
  [s]
  (read (string-push-back-reader s) true nil false))

(defn read-line
  "Reads a line from the reader or from *in* if no reader is specified"
  ([] (read-line *in*))
  ([rdr]
     (if (or (instance? LineNumberingPushbackReader rdr)
             (instance? java.io.BufferedReader rdr))
       (clojure.core/read-line rdr)
       (loop [c (read-char rdr) s (StringBuilder.)]
         (if (newline? c)
           (str s)
           (recur (read-char rdr) (.append s c)))))))
