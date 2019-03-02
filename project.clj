(defproject emtec-reports "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [com.taoensso/timbre "4.10.0"]
                 [thheller/shadow-cljs "LATEST"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.namespace "0.2.11"]
                 [aero "1.1.3"]
                 [com.cognitect/transit-cljs "LATEST"]
                 ;; back end
                 [ring/ring-jetty-adapter "1.7.1"]
                 [metosin/reitit "0.2.13"]
                 [mount "0.1.16"]
                 [ring/ring-jetty-adapter "1.7.1"]
                 [metosin/jsonista "0.2.2"]
                 [org.clojure/core.async "0.4.490"]
                 ;;Frontend

                 [reagent    "0.8.1"]
                 [secretary                   "1.2.3"]
                 [venantius/accountant         "0.2.4"]
                 [re-frame   "0.10.5"]]
;  :main ^:skip-aot emtec-reports.core

  :plugins [[lein-sass "0.4.0"]]

  :source-paths ["src/cljs" "src/clj" "src/cljc"]
  :sass {:src "resources/sass"
         :output-directory "resources/public/css"}

  :hooks [leiningen.sass]
  :aliases {"js-watch" ["run" "-m" "shadow.cljs.devtools.cli" "watch" "app"]}
  :target-path "target/%s"
  :repl-options {:port 10001}
  :profiles {:uberjar {:aot :all}})
