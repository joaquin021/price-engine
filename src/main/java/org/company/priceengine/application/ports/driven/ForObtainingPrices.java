package org.company.priceengine.application.ports.driven;

import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;

import java.util.List;

public interface ForObtainingPrices {

    List<Price> searchAvailablePrices(DataToSearchPrice dataToSearchPrice);

}
