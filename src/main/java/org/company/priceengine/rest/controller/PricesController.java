package org.company.priceengine.rest.controller;

import org.company.priceengine.persistence.entity.Price;
import org.company.priceengine.domain.model.SearchPrice;
import org.company.priceengine.rest.dto.mapper.PriceMapper;
import org.company.priceengine.rest.dto.response.SearchPriceResponse;
import org.company.priceengine.service.PriceEngineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
@RequestMapping("prices")
public class PricesController {

    private final PriceEngineService priceEngineService;
    private final PriceMapper priceMapper;

    public PricesController(PriceEngineService priceEngineService, PriceMapper priceMapper) {
        this.priceEngineService = priceEngineService;
        this.priceMapper = priceMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<SearchPriceResponse> searchPrice(@RequestParam int brandId, @RequestParam long productId, @RequestParam long applicationDate) {
        SearchPrice searchPrice = new SearchPrice(brandId, productId, new Timestamp(applicationDate));
        Price price = priceEngineService.getPrice(searchPrice);
        return ResponseEntity.ok(priceMapper.toSearchPriceResponse(searchPrice, price));
    }

}
