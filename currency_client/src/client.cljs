(ns client
  (:require [reagent.core :as r]
            [ajax.core :refer [GET POST]]
            [clojure.browser.dom :as dom]))

;; CONSTS
(def btn (.querySelector js/document "button"))

(def select {:currency-select (array-seq (.querySelector js/document ".currency-select"))
             :user-select (array-seq (.querySelector js/document ".user-select"))})

(def children (.-children (select :currency-select)))

;; FUNCS
(defn add-listener
  [el ev f]
  (.addEventListener el ev f false))

(defn append-clone-el
  [parent el]
  (.appendChild parent (.cloneNode el true)))

(defn unique?
  [el coll]
  (apply = false (map #(= (.-value el) (.-value %)) coll)))

(defn post-options 
  []
  (let [options (select :user-select) 
        input (.-value (.querySelector js/document ".form-control"))] 
    (if (and (not= input "") (not= options nil)) 
      (POST "/currency" {:body {:days input
                                :char-codes options}}) 
      nil))
  )

;; TIME TO DO
;; add listeners to all options in option list
(doseq
 [x children]
  (add-listener
   x "click"
  ;;  if el is unique then append else nil
   #(if (unique? x (select :user-select))
      (append-clone-el (select :user-select) x)
      nil)))

(add-listener btn "click" post-options)

