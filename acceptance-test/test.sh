#!/bin/bash

test() {
  local __TEST_NAME __BRAND_ID __PRODUCT_ID __APPLICATION_DATE applicationDateTimestamp
  echo "+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++"
  __TEST_NAME=$1
  __BRAND_ID=$2
  __PRODUCT_ID=$3
  __APPLICATION_DATE=$4
  echo "$__TEST_NAME"
  applicationDateTimestamp=$(date -d "$__APPLICATION_DATE" +%s%3N)
  curl -s -X GET "http://localhost:8080/prices?brandId=$__BRAND_ID&productId=$__PRODUCT_ID&applicationDate=$applicationDateTimestamp" | jq

  echo "-----------------------------------------------------------------------------------------------------------------"
  echo ""
  echo ""
  echo ""
}

wait_for_service() {
    local host="localhost"
    local port=8080
    local timeout=60
    local url="http://$host:$port/actuator/health"
    echo "Wait for the service $url ..."

    while [ $timeout -gt 0 ]; do
        if curl -s --head $url | grep "200" > /dev/null; then
            return 0
        fi
        sleep 1
        ((timeout--))
    done
    exit 1
}

java -jar -Dlogging.level.root=ERROR price-engine.jar &
pid=$!

wait_for_service

test "Test 1: request at 10:00 a.m. on the 14th for product 35455 for brand 1 (XYZ)" 1 35455 "2020-06-14 10:00:00"
test "Test 2: request at 4:00 p.m. on the 14th for product 35455 for brand 1 (XYZ)" 1 35455 "2020-06-14 16:00:00"
test "Test 3: request at 9:00 p.m. on day 14th for product 35455 for brand 1 (XYZ)" 1 35455 "2020-06-14 21:00:00"
test "Test 4: request at 10:00 a.m. on the 15th for product 35455 for brand 1 (XYZ)" 1 35455 "2020-06-15 10:00:00"
test "Test 5: request at 9:00 p.m. on day 16th for product 35455 for brand 1 (XYZ)" 1 35455 "2020-06-16 21:00:00"

kill -9 $pid