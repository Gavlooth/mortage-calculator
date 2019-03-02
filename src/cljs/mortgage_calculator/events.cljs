(ns mortgage-calculator.events
  (:require [re-frame.core :as rf]
            [mortgage-calculator.db :as db]
            [goog.json :as gjson]
            [cognitect.transit :as t]))

(def r (t/reader :json))
(def w (t/writer :json))

(rf/reg-event-fx
 ::initialize-db
  [(rf/inject-cofx :read-localStorage)]     ;; <-- this is new
  (fn [cofx _]
    (let [stored-db (:local-store cofx)
          db  (:db cofx)]
      (println "db " stored-db)
      {:db stored-db})))


(rf/reg-event-db
 ::select-mortgage
  (fn [db [_ mortgage-name]]
    (assoc db :selected-mortgage mortgage-name)))


(rf/reg-event-fx
 ::add-entry
 (fn [{:keys [db] :as cofx} [_ {:keys [entry-name] :as entry}]]
   (let [new-entry (dissoc entry entry-name)
         new-db  (assoc (update-in db [:user :mortgages] assoc entry-name new-entry) :selected-mortgage entry-name)]
     {:db new-db
      :write-localStorage new-db})))


(rf/reg-event-fx
 ::clear-store
 (fn [db _]
   {:db nil
    :write-localStorage  nil}))


(rf/reg-cofx
  :read-localStorage
  (fn [coeffects]
     (assoc coeffects :local-store (t/read r (.getItem js/localStorage "stored-mortgages")) :keywordize-keys true)))


(rf/reg-fx
 :write-localStorage
 (fn [a-value]
   (.setItem (.-localStorage js/window) "stored-mortgages" (t/write w a-value))))


(rf/reg-fx
 :clear-localStorage
 (fn [_]
   (.setItem (.-localStorage js/window) "stored-mortgages" nil)))
