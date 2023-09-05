package org.company.priceengine.service;

import org.company.priceengine.persistence.entity.Price;
import org.company.priceengine.domain.model.SearchPrice;
import org.company.priceengine.persistence.repositoty.PriceRepository;
import org.company.priceengine.exception.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class PriceEngineServiceImpl implements PriceEngineService {

    private final PriceRepository priceRepository;

    public PriceEngineServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public Price getPrice(SearchPrice searchPrice) {
        return priceRepository.findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(searchPrice.getBrandId(), searchPrice.getProductId(), searchPrice.getApplicationDate(), searchPrice.getApplicationDate()).
                stream().max(Comparator.comparingInt(Price::getPriority))
                .orElseThrow(() -> new ProductNotFoundException(searchPrice));
    }
}
