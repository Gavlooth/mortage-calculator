(ns mortgage-calculator.events
  (:require [re-frame.core :as rf]
            [mortgage-calculator.db :as db]))



(rf/reg-event-fx
 ::initialize-db
  [(rf/inject-cofx :local-store)]     ;; <-- this is new
  (fn [cofx _]
    (let [stored-db (:local-store cofx)
          db  (:db cofx)]
      {:db (assoc db :defaults stored-db)})))


(rf/reg-event-db
 ::select-mortgage
  (fn [db [_ mortgage-name]]
    (assoc db :selected-mortgage mortgage-name)))


(rf/reg-event-fx
 ::add-entry
 (fn [{:keys [db] :as cofx} [_ {:keys [entry-name] :as entry}]]
   (println entry)
   (let [new-entry (dissoc entry entry-name)
         new-db (update-in db [:user :mortgages] assoc entry-name new-entry)]
     {:db  new-db
      :write-localStorage  (clj->js new-db)
      :dispatch [::select-mortgage entry-name]})))


(rf/reg-event-fx
 ::clear-store
 (fn [db _]
   {:db nil
    :write-localStorage  nil}))



(rf/reg-cofx
  :read-localStorage
  (fn [coeffects]
     (assoc coeffects :local-store
            (js->clj (.getItem js/localStorage "stored-mortgages")))))


(rf/reg-fx
 :write-localStorage
 (fn [a-value]
   (.setItem (.-localStorage js/window) "stored-mortgages" a-value)))
