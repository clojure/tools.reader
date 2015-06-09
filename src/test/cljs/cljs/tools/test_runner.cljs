(ns cljs.tools.test-runner
  (:require cljs.tools.metadata-test
            cljs.tools.reader-test
            cljs.tools.reader-edn-test
            [cljs.test :refer-macros [run-all-tests]]))

(enable-console-print!)
(run-all-tests)
