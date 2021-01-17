(ns caesarhu.unqualify-test
  (:require [clojure.test :refer :all]
            [caesarhu.unqualify :refer :all]))

(deftest unqualify-all-test
  (testing "keyword test"
    (is (= :bank-id (unqualify :bank/bank-id)))
    (is (= :id (unqualify :employee/id)))
    (is (= :id :id)))
  (testing "symbol test"
    (is (= 'bank-id (unqualify 'bank/bank-id)))
    (is (= 'id (unqualify 'employee/id)))
    (is (= 'id 'id)))
  (testing "map test"
    (is (= {:id "id"} (unqualify {:employee/id "id"})))
    (is (= {:id {:bank-id "bank-id"
                 :changes 123}} (unqualify {:employee/id {:bank/bank-id "bank-id"
                                                          :employee/changes 123}} true)))))
