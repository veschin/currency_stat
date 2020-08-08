(ns server.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [server.views :as views]
            [org.httpkit.server :refer [run-server]]))

(defroutes currency-app
  (GET "/" [] (views/get-index-page))
    ; (GET "/currency" [] )
    ; (POST "/user-query" [] 
  (route/resources  "/"))

(defn -main []
  (run-server currency-app  {:port 5000}))
