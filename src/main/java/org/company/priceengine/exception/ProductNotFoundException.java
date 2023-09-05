package org.company.priceengine.exception;

import org.company.priceengine.domain.model.SearchPrice;

public class ProductNotFoundException extends RuntimeException {

    private final SearchPrice searchPrice;

    public ProductNotFoundException(SearchPrice searchPrice) {
        super("No price matching the search criteria was found.");
        this.searchPrice = searchPrice;
    }

    public SearchPrice getSearchPrice() {
        return searchPrice;
    }

}
