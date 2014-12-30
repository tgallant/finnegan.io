(ns finnegan.io.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.adapter.jetty :refer [run-jetty]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" []
    (->
      (resp/resource-response "index.html" {:root "build"})
      (resp/header "Content-Type" "text/html; charset=utf-8")))

  (route/resources "/" {:root "build"})
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defonce server (run-jetty #'app {:port 3000 :join? false}))
