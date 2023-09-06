package org.company.priceengine.application.exception;

import org.company.priceengine.application.model.DataToSearchPrice;

public class ProductNotFoundException extends RuntimeException {

    private final DataToSearchPrice dataToSearchPrice;

    public ProductNotFoundException(DataToSearchPrice dataToSearchPrice) {
        super("No price matching the search criteria was found.");
        this.dataToSearchPrice = dataToSearchPrice;
    }

    public DataToSearchPrice getSearchPrice() {
        return dataToSearchPrice;
    }

}
