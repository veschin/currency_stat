(ns currency-stat.request
  (:require [clj-http.client :as client])
  (:require [clojure.data.json :as json]))

(defn nominal-to-one
  [v n]
  (float (/ v n)))

; [int string [list of keywords]] -> list of {:day N :CharCode Value} 
(defn get-currency
  [days url currency-keys]
  (loop [days days
         url url
         acc []]

    (let [api-map (json/read-str ((client/get url) :body) :key-fn keyword)
          currency (api-map :Valute)
          prev-url (str "https:" (api-map :PreviousURL))
          values (map #(nominal-to-one ((currency %) :Value) ((currency %) :Nominal)) currency-keys)]

      (if (zero? days)
        acc
        (recur
         (dec days) prev-url (->> values
                                  (zipmap currency-keys)
                                  (into {:date (api-map :Timestamp)
                                         :day days})
                                  (conj acc)))))))











