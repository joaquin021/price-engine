package org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.mapper;

import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;
import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.response.SearchPriceResponse;
import org.springframework.stereotype.Component;

@Component
public class SearchPriceResponseMapper {

    public SearchPriceResponse toSearchPriceResponse(DataToSearchPrice dataToSearchPrice, Price price) {
        return new SearchPriceResponse.Builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .price(price.getPrice())
                .applicationDate(dataToSearchPrice.getApplicationDate().getTime())
                .build();
    }

}
