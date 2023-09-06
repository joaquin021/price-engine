package org.company.priceengine.infrastructure.forobtainingprices.db.service;

import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;
import org.company.priceengine.application.ports.driven.ForObtainingPrices;
import org.company.priceengine.infrastructure.forobtainingprices.db.entity.PriceEntity;
import org.company.priceengine.infrastructure.forobtainingprices.db.mapper.PriceMapper;
import org.company.priceengine.infrastructure.forobtainingprices.db.repositoty.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PricePersistenceAdapter implements ForObtainingPrices {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    public PricePersistenceAdapter(PriceRepository priceRepository, PriceMapper priceMapper) {
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
    }

    @Override
    public List<Price> searchAvailablePrices(DataToSearchPrice dataToSearchPrice) {
        List<PriceEntity> prices = priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(dataToSearchPrice.getBrandId(), dataToSearchPrice.getProductId(), dataToSearchPrice.getApplicationDate(), dataToSearchPrice.getApplicationDate());
        return prices.stream().map(priceMapper::toPrice).collect(Collectors.toList());
    }

}
