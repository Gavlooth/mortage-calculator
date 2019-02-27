(ns mortage-calculator.app
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [goog.dom :as gdom]
            [mortage-calculator.views :as views]))

(defn ^:export main [ & more]
  #_(routes/setup-routing!)
  (r/render
   [:<>
    [views/navbar]
    [views/instructions]]
   (gdom/getElement "app")))


(main)

