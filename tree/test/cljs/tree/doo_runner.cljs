(ns tree.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [tree.core-test]))

(doo-tests 'tree.core-test)

