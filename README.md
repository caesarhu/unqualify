# unqualify

A protocol to unqualify clojure object, suport keyword symbol and map.

## Usage

```clojure
(unqualify obj recursive?) ;; default recursive? false
or
(unqualify obj)

Keyword
(unqualify :bank/id)
;; :id

Symbol
(unqualify 'bank/id)
;; id

Map
(unqualify {:employee/id "id"})
;; {:id "id"}
(unqualify {:employee/id {:bank/bank-id "bank-id"
                          :employee/changes 123}} true)
;; {:id {:bank-id "bank-id" :changes 123}}
```

Invoke a library API function from the command-line:

    $ clojure -X caesarhu.unqualify/foo :a 1 :b '"two"'
    {:a 1, :b "two"} "Hello, World!"

Run the project's tests (they'll fail until you edit them):

    $ clojure -M:test:runner

Build a deployable jar of this library:

    $ clojure -X:jar

Install it locally:

    $ clojure -M:install

Deploy it to Clojars -- needs `CLOJARS_USERNAME` and `CLOJARS_PASSWORD` environment variables:

    $ clojure -M:deploy

## License

Copyright © 2021 Shun

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
