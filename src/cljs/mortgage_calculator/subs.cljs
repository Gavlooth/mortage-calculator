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
     (println x)
     (println y)
     (let [{:keys [deposit-paid  principal-of-loan  interest-rate]} (get y x)
           monthly-frm (u/monthly-FRM  principal-of-loan  interest-rate deposit-paid)
           remaining-loan (-  principal-of-loan deposit-paid)]
       (let [yearly-frm (* monthly-frm 12)]
        (vector monthly-frm (for [i (range 1 5)]
                             (let [frm (* i yearly-frm)
                                   portion-paid (/ frm remaining-loan)]
                               [portion-paid  (- 1 portion-paid)]))))))))
