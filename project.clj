(defproject currency_stat "0.1.0-SNAPSHOT"
  :description "N day currency statistics with data visualization. Clojure - backend. ClojureScript - client side."
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "1.0.0"]
                 [clj-http "3.10.1"]
                 [org.clojure/java.jdbc "0.7.11"]
                 [org.xerial/sqlite-jdbc "3.32.3.2"]]
  :repl-options {:init-ns server.core})
