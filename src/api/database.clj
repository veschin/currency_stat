(ns api.database
  (:require [clojure.java.jdbc :as jdbc]))

(def db
  {; :classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/currency.db"})

(defn create-db
  [currency-keys table]
  (try

    (comment
      currency table structure
      :currency
      [[:id :int :primary :key :autoincrement]
       [:date :text]
       [:day :int]
       ->> threading macro generate N amount of [:currency-name :float]
       [:currency-name :float]
       [:currency-name :float]
       [:currency-name :float]
       [:currency-name :float]])

    (jdbc/db-do-commands
     db
     [(jdbc/drop-table-ddl table {:conditional? true})
      (jdbc/create-table-ddl
       table
       (->> currency-keys
            (map
             #(->> % (conj [:float]) (reverse) (vec)))
            (concat [[:date :text]
                     [:day :int]])
            (vec)))])

    (catch Exception e
      (println (.getMessage e)))))

(defn insert-rows
  [rows table]
  (jdbc/insert-multi! db table rows))

