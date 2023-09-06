package org.company.priceengine.infrastructure.forcustomerslookingprice.rest.controller;

import org.company.priceengine.application.model.DataToSearchPrice;
import org.company.priceengine.application.model.Price;
import org.company.priceengine.application.ports.driving.ForCustomersLookingPrice;
import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.mapper.SearchPriceResponseMapper;
import org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.response.SearchPriceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("prices")
public class PricesController {

    private final ForCustomersLookingPrice forCustomersLookingPrice;
    private final SearchPriceResponseMapper searchPriceResponseMapper;

    public PricesController(ForCustomersLookingPrice forCustomersLookingPrice, SearchPriceResponseMapper searchPriceResponseMapper) {
        this.forCustomersLookingPrice = forCustomersLookingPrice;
        this.searchPriceResponseMapper = searchPriceResponseMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<SearchPriceResponse> searchPrice(@RequestParam int brandId, @RequestParam long productId, @RequestParam long applicationDate) {
        DataToSearchPrice dataToSearchPrice = new DataToSearchPrice(brandId, productId, new Timestamp(applicationDate));
        Price price = forCustomersLookingPrice.getPrice(dataToSearchPrice);
        return ResponseEntity.ok(searchPriceResponseMapper.toSearchPriceResponse(dataToSearchPrice, price));
    }

}
