(ns tree.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[tree started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[tree has shut down successfully]=-"))
   :middleware identity})
