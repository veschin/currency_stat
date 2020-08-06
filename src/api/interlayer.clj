(ns api.interlayer
  (:require [api.request :as req])
  (:require [api.database :as db]))

;const
(def days 30)

(def currency-keys [:USD :EUR :CNY :JPY])

(def url "https://www.cbr-xml-daily.ru/daily_json.js")

(def currency-rows (req/get-currency days url currency-keys))

(defn fill-database
  [table]
  (do
    (db/create-db currency-keys table)
    (db/insert-rows currency-rows table)))







