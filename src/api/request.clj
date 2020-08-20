(ns api.request
  (:require [clj-http.client :as client]
            [clojure.data.json :as json]))

(defn nominal-to-one
  [v n]
  (float (/ v n)))

(def url "https://www.cbr-xml-daily.ru/daily_json.js")

; [vector of char codes]
(defn get-char-codes []
  (let [api-map (json/read-str ((client/get url) :body) :key-fn keyword)
        currency (api-map :Valute)
        names (map #((currency %) :Name) (keys currency))
        char-codes (keys currency)]
    (map vector names char-codes)))

; [int string [list of keywords]] -> [vector of {:day N :CharCode Value}] 
(defn get-currency
  [days char-codes]
  (loop [days days
         url url
         acc []]

    (let [api-map (json/read-str ((client/get url) :body) :key-fn keyword)
          currency (api-map :Valute)
          prev-url (str "https:" (api-map :PreviousURL))
          values (map
                  #(format "%.2f"
                           (nominal-to-one ((currency %) :Value) ((currency %) :Nominal))) char-codes)]

      (if (zero? days)
        acc
        (recur
         (dec days) prev-url (->> values
                                  (zipmap char-codes)
                                  (assoc {:date (subs (api-map :Timestamp) 0 10)
                                          :day days} :values)
                                  (conj acc)))))))











