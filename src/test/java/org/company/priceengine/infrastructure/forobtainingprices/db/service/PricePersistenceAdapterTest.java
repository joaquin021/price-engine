package org.company.priceengine.infrastructure.forobtainingprices.db.service;

import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;
import org.company.priceengine.infrastructure.forobtainingprices.db.entity.PriceEntity;
import org.company.priceengine.infrastructure.forobtainingprices.db.mapper.PriceMapper;
import org.company.priceengine.infrastructure.forobtainingprices.db.repositoty.PriceRepository;
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
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PricePersistenceAdapterTest {

    private PricePersistenceAdapter pricePersistenceAdapter;
    @Mock
    private PriceRepository priceRepositoryMock;

    @BeforeEach
    void init() {
        pricePersistenceAdapter = new PricePersistenceAdapter(priceRepositoryMock, new PriceMapper());
    }

    @Test
    void notFoundPrices() {
        Timestamp applicationDate = new Timestamp(45L);
        DataToSearchPrice dataToSearchPrice = new DataToSearchPrice(1, 1, applicationDate);
        List<PriceEntity> priceEntities = new ArrayList<>();
        Mockito.when(priceRepositoryMock.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1, applicationDate, applicationDate)).thenReturn(priceEntities);

        List<Price> prices = pricePersistenceAdapter.searchAvailablePrices(dataToSearchPrice);

        assertEquals(0, prices.size());
    }

    @Test
    void pricesFoundAndMappedCorrectly() {
        Timestamp applicationDate = new Timestamp(45L);
        DataToSearchPrice dataToSearchPrice = new DataToSearchPrice(1, 1, applicationDate);
        List<PriceEntity> priceEntities = new ArrayList<>();
        priceEntities.add(createPriceEntity(2, "45.54"));
        priceEntities.add(createPriceEntity(0, "23.23"));
        Mockito.when(priceRepositoryMock.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(1, 1, applicationDate, applicationDate)).thenReturn(priceEntities);

        List<Price> prices = pricePersistenceAdapter.searchAvailablePrices(dataToSearchPrice);

        assertEquals(2, prices.size());
        assertEquals(2, prices.get(0).getPriority());
        assertEquals("45.54", prices.get(0).getPrice().toPlainString());
        assertEquals(0, prices.get(1).getPriority());
        assertEquals("23.23", prices.get(1).getPrice().toPlainString());
    }

    private PriceEntity createPriceEntity(int priority, String price) {
        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setId(new Random().nextInt());
        priceEntity.setBrandId(1);
        priceEntity.setProductId(1);
        priceEntity.setPriority(priority);
        priceEntity.setPrice(new BigDecimal(price));
        priceEntity.setCurrency("EUR");
        priceEntity.setStartDate(new Timestamp(1L));
        priceEntity.setEndDate(new Timestamp(50L));
        return priceEntity;
    }


}