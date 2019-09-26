(ns tree.events
  (:require
    [re-frame.core :as rf]
    [ajax.core :as ajax]))

;;dispatchers

(rf/reg-event-db
  :initial-db
  (fn [db _]
    {:counter 0
    :delta 1}))

(rf/reg-event-db
  :navigate
  (fn [db [_ route]]
    (assoc db :route route)))

(rf/reg-event-db
  :set-docs
  (fn [db [_ docs]]
    (assoc db :docs docs)))

(rf/reg-event-fx
  :fetch-docs
  (fn [_ _]
    {:http-xhrio {:method          :get
                  :uri             "/docs"
                  :response-format (ajax/raw-response-format)
                  :on-success       [:set-docs]}}))

(rf/reg-event-db
  :common/set-error
  (fn [db [_ error]]
    (assoc db :common/error error)))

(rf/reg-event-db
  :inc
  (fn [db [_ _]]
  (assoc db :counter (+ (:counter db) (:delta db)))))

(rf/reg-event-db
 :reset
 (fn [db [_ _]]
 (-> db 
  (assoc :counter 0)
  (assoc :delta 1))))

(rf/reg-event-db
 :delta
 (fn [db [x delta]]
    (js/console.log  x delta)
    (assoc db :delta 
      (if 
      (not (js/isNaN (js/parseInt delta)))
        (js/parseInt delta)
        0))))

(rf/reg-event-db
  :dec
  (fn [db [_ _]]
  (assoc db :counter (- (:counter db) 1))))

;;subscriptions

(rf/reg-sub
  :counter
  (fn [db _]
    (-> db :counter)))

(rf/reg-sub
  :delta
  (fn [db _]
    (-> db :delta)))

(rf/reg-sub
  :route
  (fn [db _]
    (-> db :route)))

(rf/reg-sub
  :page
  :<- [:route]
  (fn [route _]
    (-> route :data :name)))

(rf/reg-sub
  :docs
  (fn [db _]
    (:docs db)))

(rf/reg-sub
  :common/error
  (fn [db _]
    (:common/error db)))
