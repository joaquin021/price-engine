package org.company.priceengine.application.ports.driving;


import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;

public interface ForCustomersLookingPrice {

    Price getPrice(DataToSearchPrice dataToSearchPrice);


}
