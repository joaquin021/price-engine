package org.company.priceengine;

import org.company.priceengine.application.exception.ProductNotFoundException;
import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.controller.PricesController;
import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.response.SearchPriceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PriceEngineApplicationTests {

    @Autowired
    private PricesController pricesController;

    @Test
    void contextLoads() {
        assertNotNull(pricesController);
    }

    @Test
    @DisplayName("Test 1: request at 10:00 a.m. on the 14th for product 35455 for brand 1 (XYZ)")
    void test1() throws Exception {
        long applicationDate = getDate("2020-06-14 10:00:00");
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, applicationDate);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(0, searchPriceResponse.getRateToApply());
        assertEquals("35.50", searchPriceResponse.getPrice());
        assertEquals(applicationDate, searchPriceResponse.getApplicationDate());
    }

    @Test
    @DisplayName("Test 1: request at 10:00 a.m. on the 14th for product 35455 for brand 1 (XYZ)")
    void test2() throws Exception {
        long applicationDate = getDate("2020-06-14 16:00:00");
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, applicationDate);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(1, searchPriceResponse.getRateToApply());
        assertEquals("25.45", searchPriceResponse.getPrice());
        assertEquals(applicationDate, searchPriceResponse.getApplicationDate());
    }

    @Test
    @DisplayName("Test 3: request at 9:00 p.m. on day 14th for product 35455 for brand 1 (XYZ)")
    void test3() throws Exception {
        long applicationDate = getDate("2020-06-14 21:00:00");
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, applicationDate);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(0, searchPriceResponse.getRateToApply());
        assertEquals("35.50", searchPriceResponse.getPrice());
        assertEquals(applicationDate, searchPriceResponse.getApplicationDate());
    }

    @Test
    @DisplayName("Test 4: request at 10:00 a.m. on the 15th for product 35455 for brand 1 (XYZ)")
    void test4() throws Exception {
        long applicationDate = getDate("2020-06-15 10:00:00");
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, applicationDate);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(1, searchPriceResponse.getRateToApply());
        assertEquals("30.50", searchPriceResponse.getPrice());
        assertEquals(applicationDate, searchPriceResponse.getApplicationDate());
    }

    @Test
    @DisplayName("Test 5: request at 9:00 p.m. on day 16th for product 35455 for brand 1 (XYZ)")
    void test5() throws Exception {
        long applicationDate = getDate("2020-06-16 21:00:00");
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, applicationDate);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(1, searchPriceResponse.getRateToApply());
        assertEquals("38.95", searchPriceResponse.getPrice());
        assertEquals(applicationDate, searchPriceResponse.getApplicationDate());
    }

    @Test
    void test_product_not_found() {
        assertThrows(ProductNotFoundException.class, () -> pricesController.searchPrice(0, 0, 0L));
    }

    private long getDate(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date parsedDate = dateFormat.parse(date);
        return parsedDate.getTime();
    }


}
