(ns mortage-calculator.events
  (:require [re-frame.core :as rf]
            [mortage-calculator.db :as db]))



(rf/reg-event-fx
 ::initialize-db
  [(rf/inject-cofx :local-store)]     ;; <-- this is new
  (fn [cofx _]
    (let [stored-db (:local-store cofx)
          db  (:db cofx)]
      {:db (assoc db :defaults stored-db)})))


(rf/reg-event-fx
 ::add-entry
 (fn [{:keys [db] :as cofx} [_ {:keys [entry-name] :as entry}]]
   (println entry)
   (let [entry* (dissoc entry entry-name)
         new-db (update-in db [:user :mortages] assoc entry-name entry*)]
    {:db  new-db
     :write-localStorage  (clj->js new-db)})))


(rf/reg-cofx
  :read-localStorage
  (fn [coeffects]
     (assoc coeffects :local-store
            (js->clj (.getItem js/localStorage "stored-mortages")))))


(rf/reg-fx
 :write-localStorage
 (fn [a-value]
   (.setItem (.-localStorage js/window) "stored-mortages" a-value)))






