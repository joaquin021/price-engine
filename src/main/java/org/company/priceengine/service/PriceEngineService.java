package org.company.priceengine.service;

import org.company.priceengine.persistence.entity.Price;
import org.company.priceengine.domain.model.SearchPrice;

public interface PriceEngineService {

    Price getPrice(SearchPrice searchPrice);

}
