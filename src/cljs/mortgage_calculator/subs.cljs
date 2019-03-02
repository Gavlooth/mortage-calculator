(ns mortgage-calculator.subs
  (:require [re-frame.core :as rf]
            [mortgage-calculator.utils :as u]))

(rf/reg-sub
  ::mortgage-list
    (fn [db _]
     (:mortgages (:user  db))))


(rf/reg-sub
  ::selected-mortgage
    (fn [db _]
      (:selected-mortgage db)))


(rf/reg-sub
  ::yearly-statistics
  :<- [::selected-mortgage]
  :<- [::mortgage-list]
  (fn [[x y] _]
   (when (and x y)
     (let [{:keys [deposit-paid  principal-of-loan interest-rate]} (get y x)
           r (/ interest-rate 100)
           p (- principal-of-loan deposit-paid)
           c (u/monthly-payment r (* 12 5) p)
           total (reduce + (repeat 60 c))]
       (println "five year " total)
       [c (vec (for [i (range 1 5)]
                 [ (* 100 (/ (* i 12 c) total ) )   (* 100 (/ (u/monthly-remainder r (* i 12) p c) total))]))]))))

