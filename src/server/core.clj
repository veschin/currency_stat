(ns server.core
  (:require [compojure.core :as ccore]
            [compojure.route :as route]
            [server.views :as views]
            [org.httpkit.server :refer [run-server]]
            [clojure.data.json :as json]))

(defonce server (atom nil))

(ccore/defroutes currency-app
  (ccore/GET "/" [] (views/get-index-page))
  (ccore/POST "/currency" req
    (let [req (json/read-str (slurp (req :body)) :key-fn keyword)
          days (Integer. (req :days))
          char-codes (map keyword (req :char-codes))] (println days char-codes)
      (views/get-currency days char-codes)))
  (route/resources "/"))


(defn stop-server []
  (when-not (nil? @server)
    (@server :timeout 100)
    (reset! server (run-server #'currency-app {:port 5020}))))

(defn -main [& args]
  (reset! server (run-server #'currency-app {:port 5020})))
