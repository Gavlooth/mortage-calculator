(ns mortage-calculator.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]))




(defn navbar []
 [:nav.navbar
  {:aria-label "main navigation", :role "navigation"}
  [:div.navbar-brand
   [:span.navbar-item {:href "https://www.lifecheq.co.za/"}
    [:img { :width "128"
           :object-fit "contain"
           :alt "lifeCheq"
           :src "/images/lifecheq.svg"}]]
   [:div {:style {:display :flex :align-items :center :height :80%}}
    [:span.is-size-2.has-text-centered "lifeCheq"]]
   [:a.navbar-burger
    {:aria-expanded "false", :aria-label "menu", :role "button"}
    [:span  "lifeCheq"]   {:aria-hidden "false"}
    [:span {:aria-hidden "true"}]
    [:span {:aria-hidden "true"}]]]])


(defn instructions []
   [:section.section
    [:div.container
     [:h1.title "Section"]
     [:h2.subtitle
      " A simple container to divide your page into "
      [:strong "sections"]
      ", like the one you're currently reading"]]])


