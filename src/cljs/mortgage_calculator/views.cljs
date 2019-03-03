(ns mortgage-calculator.views
  (:require [reagent.core :as r]
            [re-frame.core :as rf]
            [mortgage-calculator.events :as events]
            [mortgage-calculator.utils :as u]
            [goog.dom :as gdom]
            [goog.json :as gjson]
            [mortgage-calculator.charts :as charts]))


(defn navbar []
 [:nav.navbar
  {:aria-label "main navigation", :role "navigation"}
  [:div.navbar-brand
   [:span.navbar-item {:href "#"}
    [:img { :width "128"
           :alt "Mortgage calculator"
           :src "/images/mortgage.svg"}]]
   [:div.columns.is-vcentered
    [:span.is-size-2.has-text-centered "Mortgage calculator"]]
   [:a.navbar-burger
    {:aria-expanded "false", :aria-label "menu", :role "button"}
    [:span  "Mortgage calculator"]   {:aria-hidden "false"}
    [:span {:aria-hidden "true"}]
    [:span {:aria-hidden "true"}]]]])


(defn instructions []
   [:section.section
    [:div.container;.columns.is-centered
     [:h1.title "How to use it"]
     [:h2.subtitle
      "This is a simple  calculator for your mortgage expenses "]]])


(defn- form-values->clj [the-form]
 (let [form (js->clj (.toObject
                       (.getFormDataMap ^js gdom/forms the-form))
                     :keywordize-keys true)]
   (into {} (map (fn [[x [y]]] (vector x y)) (seq form)))))


(defn- submit-fn [event]
  (.preventDefault event)
  (let [this-form (u/oget  event 'target)]
    (rf/dispatch [::events/add-entry (form-values->clj this-form)])))


(defn list-of-mortgages [mortgage-list]
    [:div.column.is-3
     [:br]
     [:div.columns
      [:div.column.is-12.has-text-centered.is-size-4 [:span "Saved mortgages"]]
      [:div.column.is-3 [:button.button.is-primary.is-size-7 {:on-click #(rf/dispatch [::events/clear-store])} "CLEAR ALL"]]]
     [:div.tags
      (for [[x1 _]  (seq mortgage-list)]
       [:div.tag
        [:div.tags.has-addons
         [:span.tag {:key (str x1 '+supercalifragilisticexpialidocious)
                     :on-click #(rf/dispatch [::events/select-mortgage x1])
                     :style {:cursor :pointer}} x1]
         [:span.tag.is-delete {:on-click #(rf/dispatch [::events/remove-entry x1])}]]])
      [:div]]])


(defn mortage-variable-field [field-label field-key pattern]
     [:div.columns.is-mobile.has-background-grey-lighter   {:key (str field-label '+ field-key)}
      [:div.column.is-half.has-text-centered [:span field-label]]
      [:div.column.is-half
       [:div.field
        [:div.control
         [:input.input.is-primary.is-medium.is-size-6
          {:name  field-key
           :pattern pattern}]]]]])


(defn calculator [selected-mortgage mortgage-list]
 (let [pattern "(\\d+\\.?)?\\d+"
       field-titles ["Loan's principal" "Deposit paid" "Interest rate %"]
       field-keys [ :principal-of-loan :deposit-paid :interest-rate]]
  [:section.section
   [:div.columns
    [:div.column.is-2]
    [:form.column.is-4.mortgage-form {:on-submit submit-fn}
     [:br]
     [:div.columns
      [:div.column.is-12.has-text-centered.is-size-4
       [:span "Mortgage Calculator"] [:span]]]
     (map mortage-variable-field  field-titles field-keys (repeat pattern))
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
    [list-of-mortgages mortgage-list]]]))


(defn mortgage-information [[monthly-payment  a-list]]
  [:section.section
   [:div.columns
    [:div.column.is-2]
    [:div.column.is-4
     [:div.is-size-5.has-text-centered
      "Monthly payment " (/ (int (* 100  monthly-payment)) 100)]
     [:table.table.is-bordered.is-striped.is-narrow.is-hoverable.is-fullwidth
      [:thead [:tr [:th.has-text-centered  "Percentage Paid"]
               [:th.has-text-centered  "Percentage Remaining"]]]
      [:tbody
       (for [[paid remaining] a-list]
         [:tr {:key (str paid "+" remaining)}
          [:td.has-text-centered   (int paid) " %"]
          [:td.has-text-centered  (int remaining) " %"]])]]]]])


(defn mortgage-bar-chart [series]
   [:section.section
    [:div.container
     [:h1.title "Loan Chart"]
     [:h2.subtitle
      " A simple Bar Chart showing the remaining "
      [:strong "loan"]
      ", for each year"]
     [charts/mortgage-chart series]]])
