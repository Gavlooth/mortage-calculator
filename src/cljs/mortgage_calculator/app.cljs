(ns mortgage-calculator.app
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [goog.dom :as gdom]
            [mortgage-calculator.events :as events]
            [mortgage-calculator.views :as views]
            [mortgage-calculator.subs :as subs]))



(defn app []
 (let [mortgage-list  @(rf/subscribe [::subs/mortgage-list])
       selected-mortgage @(rf/subscribe [::subs/selected-mortgage])
       loan-information @(rf/subscribe [::subs/yearly-statistics])
       series @(rf/subscribe [::subs/series])]
   [:<>
    [views/navbar]
    [views/instructions]
    [views/calculator selected-mortgage mortgage-list]
    [views/mortgage-information loan-information]
    [views/mortgage-bar-chart series]]))


(defn ^:export main [ & more]
  (rf/dispatch [::events/initialize-db])
  (r/render
   [app]
   (gdom/getElement "app")))


(main)

