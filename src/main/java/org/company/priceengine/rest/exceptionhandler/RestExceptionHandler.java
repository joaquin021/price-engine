package org.company.priceengine.rest.exceptionhandler;

import org.company.priceengine.exception.ProductNotFoundException;
import org.company.priceengine.rest.dto.response.SearchPriceErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    protected ResponseEntity<SearchPriceErrorResponse> handleProductNotFoundException(ProductNotFoundException productNotFoundException) {
        SearchPriceErrorResponse searchPriceErrorResponse = new SearchPriceErrorResponse.Builder()
                .brandId(productNotFoundException.getSearchPrice().getBrandId())
                .productId(productNotFoundException.getSearchPrice().getProductId())
                .applicationDate(productNotFoundException.getSearchPrice().getApplicationDate().getTime())
                .message(productNotFoundException.getMessage())
                .build();
        return new ResponseEntity<>(searchPriceErrorResponse, HttpStatus.NOT_FOUND);
    }

}
