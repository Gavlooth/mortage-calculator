(ns mortgage-calculator.utils
   (:require  [goog.object :as gobj]))


(def name* (fnil name ""))


(defn oget-in [object the-keys]
 (let [valid-keys (mapv name* the-keys)]
  (gobj/getValueByKeys object (apply array valid-keys))))


(defn oget
  ([object the-key](gobj/get object (name* the-key)))
  ([object the-key not-found] (or (gobj/get object (name* the-key) not-found))))


(defn ovalues
  ([object] (gobj/getValues object))
  ([object key-seq] (map (partial oget object) key-seq)))


(defn oset! [object the-key the-val]
  (gobj/set object (name* the-key ) the-val)
  object)


(defn oset-in! [object the-keys the-value]
 (let [length-1 (dec (count the-keys))
       butlast-keys  (subvec the-keys 0 length-1)
       last-key (name* (last the-keys))]
  (try
   (do (gobj/set (oget-in object butlast-keys) last-key the-value)
       object)
   (catch js/Object e (.log js/console"Error: " e  {:color "red"})))))


(defn monthly-FRM
  ([principal-amount interest-rate]
   (monthly-FRM  principal-amount interest-rate 0))
  ([principal-amount interest-rate deposit-paid]
   (let [loan-left (- principal-amount deposit-paid)]
     (cond (zero? interest-rate)(/ loan-left 60)
           (pos? interest-rate) (/ loan-left (- 1 (Math/pow (inc (* interest-rate  100)) -60)))
           :default 0))))



