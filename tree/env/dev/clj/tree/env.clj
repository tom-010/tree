(ns tree.env
  (:require
    [selmer.parser :as parser]
    [clojure.tools.logging :as log]
    [tree.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[tree started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[tree has shut down successfully]=-"))
   :middleware wrap-dev})
