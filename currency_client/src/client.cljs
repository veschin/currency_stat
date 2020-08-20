(ns client
  (:require [ajax.core :refer [GET POST]]
            [cljs.tools.reader.edn :as edn]))

;; CONSTS
(def btn (.querySelector js/document "button"))

(def select {:currency-select (.querySelector js/document ".currency-select")
             :user-select (.querySelector js/document ".user-select")})

(def children (array-seq (.-children (select :currency-select))))

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

(defn listen-response [resp]
  (js/console.log  (str (map #(% :date) (edn/read-string resp))))
  )

(defn post-currency
  []
  (let [char-codes (.-children (select :user-select))
        days (.-value (.querySelector js/document ".form-control"))]
    
    (if (and (not= days "") (not= (.-length char-codes) nil))

      (POST "/currency" {:format :json
                         :handler listen-response
                         
                         :body (.stringify js/JSON
                                           (clj->js
                                            {:days days
                                             :char-codes (map #(subs (.-value %) 1) (array-seq char-codes))}))})
      nil)))

;; TIME TO DO
;; add listeners to all options in option list
(doseq
 [x children]
  (add-listener
   x "click"
   ;;  if el is unique then append else nil
   #(if (unique? x (array-seq (select :user-select)))
      (append-clone-el (select :user-select) x)
      nil)))

(add-listener btn "click" post-currency)

