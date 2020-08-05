(ns currency-stat.core
    (:require [currency-stat.request :as req])
    (:require [currency-stat.db :as db])
    (:gen-class))

(def url "https://www.cbr-xml-daily.ru/daily_json.js")
(def currency-keys [:USD :EUR :CNY :JPY])

(req/get-currency 30 url currency-keys)
