package org.company.priceengine.rest.dto.mapper;

import org.company.priceengine.persistence.entity.Price;
import org.company.priceengine.domain.model.SearchPrice;
import org.company.priceengine.rest.dto.response.SearchPriceResponse;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public SearchPriceResponse toSearchPriceResponse(SearchPrice searchPrice, Price price) {
        return new SearchPriceResponse.Builder()
                .brandId(price.getBrandId())
                .productId(price.getProductId())
                .price(price.getPrice())
                .applicationDate(searchPrice.getApplicationDate().getTime())
                .build();
    }

}
