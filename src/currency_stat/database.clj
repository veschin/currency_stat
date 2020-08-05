(ns currency-stat.database
  (:require [clojure.java.jdbc :as jdbc]))

(def db
  {:classname   "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname     "db/currency.db"})

(defn drop-table [] (jdbc/drop-table-ddl :currency))

(defn create-db
  [currency-keys]
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
     (jdbc/create-table-ddl
      :currency
      (->> currency-keys
           (map
            #(->> % (conj [:float]) (reverse) (vec)))
           (concat [[:id :int :primary :key :autoincrement]
                    [:date :text]
                    [:day :int]])
           (vec))))

    (catch Exception e
      (println (.getMessage e)))))

(defn insert-rows
  [rows]
  (jdbc/insert-multi! db :currency rows))


