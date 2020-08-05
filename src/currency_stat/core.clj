(ns currency-stat.core
  (:require [currency-stat.request :as req])
  (:require [currency-stat.database :as db])
  (:gen-class))

;const
(def url "https://www.cbr-xml-daily.ru/daily_json.js")

(def currency-keys [:USD :EUR :CNY :JPY])

(def days 30)

; db section
(try (do
       (db/drop-table)
       (db/create-db currency-keys))
     (catch db/create-db currency-keys))

(db/insert-rows (req/get-currency days url currency-keys))
