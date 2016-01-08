(defproject ipexploder "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.csv "0.1.3"]]
  :repl-options {:init-ns user}
  :profiles {:dev {:source-paths ["dev"]}}
  :jvm-opts ["-Xmx8G" "-server"
             "-Dcom.sun.management.jmxremote"
             "-Dcom.sun.management.jmxremote.port=5001"
             "-Dcom.sun.management.jmxremote.authenticate=false"
             "-Dcom.sun.management.jmxremote.ssl=false"])
