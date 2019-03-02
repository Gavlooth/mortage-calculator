(ns mortgage-calculator.charts
  (:require
   [reagent.core :as reagent]
   ["chartist-plugin-axistitle" :as axistitle]
   ["chartist" :as chartist]
   ["react-chartist"  :default ChartistGraph]))




(def labels #js  ["Year 1" "Year 2" "Year 3" "Year 4"])



(def chart-options  {:low 0
                     :showArea true
                     :height "500px"})


(defn mortgage-chart [series]
 (when series
   [:> ChartistGraph {:data {:labels labels
                             :series series}
                      :options chart-options
                      :type "Bar"}]))

