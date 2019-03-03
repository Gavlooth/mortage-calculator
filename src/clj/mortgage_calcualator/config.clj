(ns mortgage-calculator.config
  (:require
    [aero.core :as aero]
    [clojure.java.io :as id]
    [mount.core :as mount]))


(defn  load-config   []
  (merge (aero/read-config  "config.edn")
         (aero/read-config (io/resource "config.edn"))))




(defstate config :start (load-config))
