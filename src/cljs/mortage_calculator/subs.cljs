(ns mortage-calculator.subs
  (:require [re-frame.core :as rf]))


(rf/reg-sub
  ::mortage-list
    (fn [db _]
     (:mortages (:user  db))))



(rf/reg-sub
  ::selected-mortage
    (fn [db _]
      (:selected-mortage db)))
