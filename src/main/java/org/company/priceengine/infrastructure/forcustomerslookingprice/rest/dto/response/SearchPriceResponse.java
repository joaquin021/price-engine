package org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.response;

import java.math.BigDecimal;

public class SearchPriceResponse {

    private final long productId;
    private final int brandId;
    private final String price;
    private final long applicationDate;

    public SearchPriceResponse(Builder builder) {
        this.productId = builder.productId;
        this.brandId = builder.brandId;
        this.price = builder.price;
        this.applicationDate = builder.applicationDate;
    }

    public long getProductId() {
        return productId;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getPrice() {
        return price;
    }

    public long getApplicationDate() {
        return applicationDate;
    }

    public static class Builder {

        private long productId;
        private int brandId;
        private String price;
        private long applicationDate;

        public Builder brandId(int brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder productId(long productId) {
            this.productId = productId;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price.toPlainString();
            return this;
        }

        public Builder applicationDate(long applicationDate) {
            this.applicationDate = applicationDate;
            return this;
        }

        public SearchPriceResponse build() {
            return new SearchPriceResponse(this);
        }

    }
}
