(ns server.views
  (:require [hiccup.page :as hpage :refer [html5]]
            [api.request :as req]))

(defn get-index-page []
  (html5
   [:head
    [:title "Главная страница"]
    (hpage/include-css "css/index.css")
    (hpage/include-css "https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css")]
   [:body
    [:div.container
     [:div.row
      [:div.canvas]]

     [:div.row
      [:div.col]
      [:select.custom-select.currency-select.mr-1.col-4 {:multiple true}

      ;;  [:option {:value :CharCode} "Name"]
       (map
        #(vec
          (concat [:option]  [{:value (str (second %))} (str (first %))])) (req/get-char-codes))]
      [:select.custom-select.user-select.ml-1.col-4 {:multiple true}]
      [:div.col]]

     [:div.row.mt-3
      [:div.col]
      [:div.input-group.col-3
       [:div.input-group-prepend
        [:span.input-group-text "Количество дней"]]
       [:input.form-control]]
      [:button.btn.btn-dark.col-2.ml-1 "Запросить данные"]
      [:div.col]]]
    (hpage/include-js "js/main.js")]))

(defn get-currency 
  [days char-codes]
    (str (req/get-currency days char-codes)))
