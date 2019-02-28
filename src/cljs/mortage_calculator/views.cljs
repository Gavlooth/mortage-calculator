(ns mortage-calculator.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [mortage-calculator.events :as events]
            [mortage-calculator.utils :as u]
            [goog.dom :as gdom]
            [goog.json :as gjson]))


;var json = goog.json.serialize(goog.dom.forms.getFormDataMap(form).toObject());
(defn navbar []
 [:nav.navbar
  {:aria-label "main navigation", :role "navigation"}
  [:div.navbar-brand
   [:span.navbar-item {:href "https://www.lifecheq.co.za/"}
    [:img { :width "128"
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
      "This is a simple  calculator for your mortage expenses "]]])


(defn- form-values->clj [the-form]
  (js->clj (.toObject (.getFormDataMap ^js gdom/forms the-form)) :keywordize-keys true))


(defn calculator [ selected-mortage mortage-list]
  [:section.section
   [:div.columns
    [:div.column.is-2]
    [:form.column.is-4.mortage-form   {:on-submit #(let [_ (.preventDefault %)
                                                         this-form (u/oget  % 'target)]
                                                     (rf/dispatch [::events/add-entry (form-values->clj this-form)]))}
     [:br]
     [:div.columns
      [:div.column.is-12.has-text-centered.is-size-4 [:span "Mortage Calculator"] [:span]]]
     [:div.columns.is-mobile.has-background-grey-lighter
      [:div.column.is-half.has-text-centered [:span "Purchase price"]]
      [:div.column.is-half
       [:div.field
        [:div.control
         [:input.input.is-primary.is-medium.is-size-5
          {:name :purchase-price}]]]]]
     [:div.columns.is-mobile.has-background-grey-lighter
      [:div.column.is-half.has-text-centered [:span "Deposit paid"]]
      [:div.column.is-half
       [:div.field
        [:div.control
         [:input.input.is-primary.is-medium.is-size-5
          {:name :deposit-paid}]]]]]
     [:div.columns.is-mobile.has-background-grey-lighter
      [:div.column.is-half.has-text-centered [:span "Yearly bond term"]]
      [:div.column.is-half
       [:div.field
        [:div.control
         [:input.input.is-primary.is-medium.is-size-5
          {:name :yearly-bond}]]]]]
     [:div.columns.is-mobile.has-background-grey-lighter
      [:div.column.is-half.has-text-centered [:span "Interest rate (fixed)"]]
      [:div.column.is-half
       [:div.field
        [:div.control
         [:input.input.is-primary.is-medium.is-size-5
          {:name :interest-rate}]]]]]

     [:div.columns.is-mobile
      [:div.column.is-3
        [:input.button.is-primary.is-size-7
         {:type "submit"
          :value "save and calculate"}]]
      [:div.column.
       [:div.field
        [:div.control
         [:input.input.is-primary.is-medium.is-size-6
          {:name :entry-name}]]]]]]

    [:div.column.is-2
     [:br]
     [:div.columns
      [:div.column.is-12.has-text-centered.is-size-4 [:span "Saved mortages"]]]
     [:div.columns.is-mobile
      [:div.column.is-half.has-text-centered [:a "mortage-1"]]]]]])



(defn mortage-bar-chart[]
   [:section.section
    [:div.container
     [:h1.title "Section"]
     [:h2.subtitle
      " A simple container to divide your page into "
      [:strong "sections"]
      ", like the one you're currently reading"]]])


