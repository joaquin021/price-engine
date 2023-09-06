package org.company.priceengine;

import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.controller.PricesController;
import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.response.SearchPriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PriceEngineApplicationTests {

    @Autowired
    private PricesController pricesController;

    @Test
    void contextLoads() {
        assertNotNull(pricesController);
    }

    @Test
    void test1() {
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, 1592121600000L);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(0, searchPriceResponse.getRateToApply());
        assertEquals("35.50", searchPriceResponse.getPrice());
        assertEquals(1592121600000L, searchPriceResponse.getApplicationDate());
    }

    @Test
    void test2() {
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, 1592143200000L);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(1, searchPriceResponse.getRateToApply());
        assertEquals("25.45", searchPriceResponse.getPrice());
        assertEquals(1592143200000L, searchPriceResponse.getApplicationDate());
    }

    @Test
    void test3() {
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, 1592161200000L);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(0, searchPriceResponse.getRateToApply());
        assertEquals("35.50", searchPriceResponse.getPrice());
        assertEquals(1592161200000L, searchPriceResponse.getApplicationDate());
    }

    @Test
    void test4() {
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, 1592208000000L);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(1, searchPriceResponse.getRateToApply());
        assertEquals("30.50", searchPriceResponse.getPrice());
        assertEquals(1592208000000L, searchPriceResponse.getApplicationDate());
    }

    @Test
    void test5() {
        ResponseEntity<SearchPriceResponse> searchPriceResponseEntity = pricesController.searchPrice(1, 35455, 1592334000000L);
        assertEquals(200, searchPriceResponseEntity.getStatusCode().value());
        SearchPriceResponse searchPriceResponse = searchPriceResponseEntity.getBody();
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(35455, searchPriceResponse.getProductId());
        assertEquals(1, searchPriceResponse.getRateToApply());
        assertEquals("38.95", searchPriceResponse.getPrice());
        assertEquals(1592334000000L, searchPriceResponse.getApplicationDate());
    }


}
