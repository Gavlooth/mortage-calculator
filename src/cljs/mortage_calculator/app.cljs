(ns mortage-calculator.app
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [goog.dom :as gdom]
            [mortage-calculator.views :as views]
            [mortage-calculator.subs :as subs]))

(defn app []
 (let [mortages  @(rf/subscribe [::subs/mortage-list])
       selected-mortage @(rf/subscribe [::subs/selected-mortage])]
   [:<>
    [views/navbar]
    [views/instructions]
    [views/calculator selected-mortage mortages]]))




(defn ^:export main [ & more]
  (r/render
   [app]
   (gdom/getElement "app")))


(main)

