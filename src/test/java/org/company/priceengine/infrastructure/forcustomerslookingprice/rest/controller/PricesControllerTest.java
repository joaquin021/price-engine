package org.company.priceengine.infrastructure.forcustomerslookingprice.rest.controller;

import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;
import org.company.priceengine.application.ports.driving.ForCustomersLookingPrice;
import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.mapper.SearchPriceResponseMapper;
import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.response.SearchPriceResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@ExtendWith(MockitoExtension.class)
class PricesControllerTest {

    private PricesController pricesController;
    @Mock
    private ForCustomersLookingPrice forCustomersLookingPriceMock;

    @BeforeEach
    void init() {
        pricesController = new PricesController(forCustomersLookingPriceMock, new SearchPriceResponseMapper());
    }

    @Test
    void searchPricesSuccessfully() {
        Price price = new Price.Builder().brandId(1).productId(1232).priority(4).price(new BigDecimal("34.45")).currency("EUR").build();
        DataToSearchPrice dataToSearchPrice = new DataToSearchPrice(1, 1232, new Timestamp(154545L));
        Mockito.when(forCustomersLookingPriceMock.getPrice(dataToSearchPrice)).thenReturn(price);

        ResponseEntity<SearchPriceResponse> priceResponseResponseEntity = pricesController.searchPrice(dataToSearchPrice.getBrandId(), dataToSearchPrice.getProductId(), dataToSearchPrice.getApplicationDate().getTime());

        SearchPriceResponse searchPriceResponse = priceResponseResponseEntity.getBody();
        assertEquals(200, priceResponseResponseEntity.getStatusCode().value());
        assertNotNull(searchPriceResponse);
        assertEquals(1, searchPriceResponse.getBrandId());
        assertEquals(1232, searchPriceResponse.getProductId());
        assertEquals("34.45", searchPriceResponse.getPrice());
        assertEquals(154545L, searchPriceResponse.getApplicationDate());
    }

}