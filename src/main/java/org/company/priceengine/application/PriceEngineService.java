package org.company.priceengine.application;

import org.company.priceengine.application.exception.ProductNotFoundException;
import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;
import org.company.priceengine.application.ports.driven.ForObtainingPrices;
import org.company.priceengine.application.ports.driving.ForCustomersLookingPrice;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class PriceEngineService implements ForCustomersLookingPrice {

    ForObtainingPrices forObtainingPrices;

    public PriceEngineService(ForObtainingPrices forObtainingPrices) {
        this.forObtainingPrices = forObtainingPrices;
    }

    @Override
    public Price getPrice(DataToSearchPrice dataToSearchPrice) {
        return forObtainingPrices.searchAvailablePrices(dataToSearchPrice)
                .stream().max(Comparator.comparingInt(Price::getPriority))
                .orElseThrow(() -> new ProductNotFoundException(dataToSearchPrice));
    }

}
