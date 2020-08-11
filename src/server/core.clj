(ns server.core
  (:require [compojure.core :as ccore]
            [compojure.route :as route]
            [server.views :as views]
            [org.httpkit.server :refer [run-server]]))

(defonce server (atom nil))

(ccore/defroutes currency-app
  (ccore/GET "/" [] (views/get-index-page))
  (ccore/POST "/currency" req (views/post-currency req))
  (route/resources "/"))


(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server nil)))


(reset! server (run-server #'currency-app {:port 5010}))
