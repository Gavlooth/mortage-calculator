(ns ken.devcards.chartist
  (:require-macros
   [devcards.core :as dc :refer [defcard defcard-rg defcard-doc]]
   [ken.macros :refer [spy generate-data]])
  (:require
   [devcards.core]
   [reagent.core :as reagent]
   [ken.utils.day-picker :as day]
   [ken.utils.charts :as charts]
   [ken.utils.utils :refer [dlog **devcards**] :as u]
   ["chartist-plugin-axistitle" :as axistitle]
   ["chartist" :as chartist]
   ["react-chartist"  :default ChartistGraph]))

;default charts
(def weekly-data
  {:labels (clj->js ["Week 1" "Week 2" "Week 3" "Week 4" "Week 5" "Week 6" "Week 7" "Week 8" "Week 9" "Week 10" "Week 11"])
   :series (charts/gen-series 3 10 200 900)})

(def daily-data
  {:labels (clj->js ["Mon" "Tue" "Wed" "Thur" "Fri" "Sat" "Sun"])
   :series (charts/gen-series 1 8 40 200)})

(def monthly-data
  {:labels (clj->js ["Jan" "Feb" "Mar" "Apr" "May" "Jun" "Jul" "Aug" "Sep" "Oct" "Nov" "Dec"])
   :series (charts/gen-series 2 13 500 2000)})


(def chart-options  {:low 0
                     :showArea true
                     :height "300px"})

(defn draw-chart [data type]
 [:> ChartistGraph {:data @data
                    :options chart-options
                    ; :plugins (js/axistitle #js {})
                    :type type}])


(defcard-rg chartist-card
  (let [data-R (reagent/atom daily-data)
        chart-type-R (reagent/atom "Bar")]
   [:div
    ; {:style {:background-color "#124354"}
     [:div.opts
      [:button {:on-click #(reset! data-R
                                   (charts/chart-data
                                    (:labels daily-data)
                                    (charts/gen-series 1 7 40 200)))} "Daily Emission"]
      [:button {:on-click #(reset! data-R
                                   (charts/chart-data
                                    (:labels weekly-data)
                                    (charts/gen-series 1 11 200 1000)))} "Weekly Emission"]
      [:button {:on-click #(reset! data-R
                                   (charts/chart-data
                                    (:labels monthly-data)
                                    (charts/gen-series 1 12 500 2000)))} "Monthly Emission"]]
    [:div.chart
     [:h2 "Bar charts"]
     [:select {:on-change #(reset! chart-type-R (-> % (.-target) (.-value)))}
      [:option {:value "Bar"} "Bar"]
      [:option {:value "Line" } "Line"]]
     [draw-chart data-R @chart-type-R]
     [:ul
        [:li.legend {:style {:color "#d70206"}} "CO2 emission"]]]]))


(defcard-rg chartist-multi-bar
  (let [data-R (reagent/atom (charts/chart-data
                                  (:labels daily-data)
                                  (charts/gen-series 4 7 40 200)))]
   [:div
    [:div.opts
     [:button {:on-click #(reset! data-R
                                  (charts/chart-data
                                   (:labels daily-data)
                                   (charts/gen-series 4 7 40 200)))} "Daily Emission"]
     [:button {:on-click #(reset! data-R
                                  (charts/chart-data
                                   (:labels weekly-data)
                                   (charts/gen-series 4 11 200 1000)))} "Weekly Emission"]
     [:button {:on-click #(reset! data-R
                                  (charts/chart-data
                                   (:labels monthly-data)
                                   (charts/gen-series 4 12 500 2000)))} "Monthly Emission"]]
    [:div.chart
     [:h2 "Mutiple Bar charts"]
     [draw-chart data-R "Bar"]
     [:ul
        [:li.legend {:style {:color "#d70206"}} "Carbon Emission"]
        [:li.legend {:style {:color "#0544d3"}} "Nitrogen Emission"]
        [:li.legend {:style {:color "#55ec33"}} "Methane"]
        [:li.legend {:style {:color "#d17905"}} "Flourinated Gases"]]]]))


(defn pie-component [pie]
 [:> ChartistGraph  {:data @pie
                     :options {:donut true
                               :donutWidth 30
                               :donutSolid true
                               :startAngle 270
                               :showLabel true}
                     :type "Pie"}
  (let [vehicles (reagent/atom (reduce + (:series @pie)))]
   [:div
    [:p "Total Number of Vehicles : " @vehicles]
    [:p "Vehicles Active : " @vehicles]])])


(defcard-rg chartist-pie
  (let [pie-data (reagent/atom {:series (clj->js [60 60 60 60])})]
    [:div
     [:h2 "Pie"]
     [:div.opts
      [:p "Vehicle information"]
      [:ul
         [:li.legend {:style {:color "#d70206"}} "Sedans"]
         [:li.legend {:style {:color "#0544d3"}} "SUV's"]
         [:li.legend {:style {:color "#55ec33"}} "MPV's"]
         [:li.legend {:style {:color "#d17905"}} "Vans"]]]
     [:div
      [:button {:on-click #(reset! pie-data {:series (first (charts/gen-series 1 4 30 330))})} "Generate"]
      [pie-component pie-data]]]))
