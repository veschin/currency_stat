(defproject currency_stat "0.2-preALFA"
  :plugins [[lein-cljsbuild "1.1.8"]]
  :description "N day currency statistics with data visualization. Clojure - backend. ClojureScript - client side."
  :dependencies [[org.clojure/clojure "1.10.1"]

                 ;crb api
                 [org.clojure/data.json "1.0.0"]
                 [clj-http "3.10.1"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [org.xerial/sqlite-jdbc "3.32.3.2"]

                 ;web server
                 [compojure "1.6.2"]
                 [http-kit "2.4.0"]

                 ;templates
                 [hiccup "1.0.5"]]


  :repl-options {:init-ns server.core})
