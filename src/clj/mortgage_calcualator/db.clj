(ns mortgage-calculator.config
  (:require
    [aero.core :as aero]
    [clojure.java.io :as id]
    [hugsql.core :as hugsql]
    [mount.core :as mount]))


(defn  load-config   []
  (merge (aero/read-config  "config.edn")
         (aero/read-config (io/resource "config.edn"))))



(def db
   {:subprotocol "h2"
      :subname (str (System/getProperty "java.io.tmpdir"
                                 "/princess_bride.h2"))})



(defstate config :start
  (load-config))
