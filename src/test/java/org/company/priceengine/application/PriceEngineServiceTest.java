package org.company.priceengine.application;

import org.company.priceengine.application.exception.ProductNotFoundException;
import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;
import org.company.priceengine.application.ports.driven.ForObtainingPrices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class PriceEngineServiceTest {

    private PriceEngineService priceEngineService;
    @Mock
    ForObtainingPrices forObtainingPricesMock;

    @BeforeEach
    void init() {
        priceEngineService = new PriceEngineService(forObtainingPricesMock);
    }

    @Test
    void priceEngineServiceReturnThePriceWithTheHighestPriority() {
        DataToSearchPrice dataToSearchPrice = new DataToSearchPrice(1, 1, new Timestamp(1L));
        List<Price> prices = new ArrayList<>();
        prices.add(new Price.Builder().brandId(1).productId(1).priority(0).price(new BigDecimal("23.22")).currency("EUR").build());
        prices.add(new Price.Builder().brandId(1).productId(1).priority(5).price(new BigDecimal("12.44")).currency("EUR").build());
        prices.add(new Price.Builder().brandId(1).productId(1).priority(3).price(new BigDecimal("67.26")).currency("EUR").build());
        prices.add(new Price.Builder().brandId(1).productId(1).priority(4).price(new BigDecimal("45.98")).currency("EUR").build());
        Mockito.when(forObtainingPricesMock.searchAvailablePrices(dataToSearchPrice)).thenReturn(prices);

        Price price = priceEngineService.getPrice(dataToSearchPrice);

        assertEquals(1, price.getBrandId());
        assertEquals(1, price.getProductId());
        assertEquals(5, price.getPriority());
        assertEquals("12.44", price.getPrice().toPlainString());
        assertEquals("EUR", price.getCurrency());
    }

    @Test
    void priceEngineServiceLaunchProductNotFoundException() {
        assertThrows(ProductNotFoundException.class, () -> {
            DataToSearchPrice dataToSearchPrice = new DataToSearchPrice(1, 1, new Timestamp(1L));
            Mockito.when(forObtainingPricesMock.searchAvailablePrices(dataToSearchPrice)).thenReturn(new ArrayList<>());
            priceEngineService.getPrice(dataToSearchPrice);
        });
    }


}