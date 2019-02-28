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
   [:div.columns.is-vcentered
    [:span.is-size-2.has-text-centered "lifeCheq"]]
   [:a.navbar-burger
    {:aria-expanded "false", :aria-label "menu", :role "button"}
    [:span  "lifeCheq"]   {:aria-hidden "false"}
    [:span {:aria-hidden "true"}]
    [:span {:aria-hidden "true"}]]]])


(defn instructions []
   [:section.section
    [:div.container;.columns.is-centered
     [:h1.title "How to use it"]
     [:h2.subtitle
      "This is a simple  calculator for your mortage expenses "
      #_[:strong " "]]]])




(defn calculator []
   [:section.section
     [:div.columns
      [:div.column.is-4]
      [:div.column.is-4
       [:br]
       [:div.columns
        [:div.column.is-12.has-text-centered.is-size-4 [:span "Mortage Calculator"] [:span]]]
       [:div.columns.is-mobile.has-background-grey-lighter
        [:div.column.is-half.has-text-centered [:span "Purchase price"]]
        [:div.column.is-half
         [:div.field
          [:div.control
           [:input.input.is-primary.is-medium.is-size-5]]]]]
       [:div.columns.is-mobile.has-background-grey-lighter
        [:div.column.is-half.has-text-centered [:span "Deposit paid"]]
        [:div.column.is-half
         [:div.field
          [:div.control
           [:input.input.is-primary.is-medium.is-size-5]]]]]

       [:div.columns.is-mobile.has-background-grey-lighter
        [:div.column.is-half.has-text-centered [:span "Yearly bond term"]]
        [:div.column.is-half
         [:div.field
          [:div.control
           [:input.input.is-primary.is-medium.is-size-5]]]]]

       [:div.columns.is-mobile.has-background-grey-lighter
        [:div.column.is-half.has-text-centered [:span "Interest rate (fixed)"]]
        [:div.column.is-half
         [:div.field
          [:div.control
           [:input.input.is-primary.is-medium.is-size-5]]]]]]]])








(defn mortage-bar-chart[]
   [:section.section
    [:div.container
     [:h1.title "Section"]
     [:h2.subtitle
      " A simple container to divide your page into "
      [:strong "sections"]
      ", like the one you're currently reading"]]])


