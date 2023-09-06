package org.company.priceengine.infrastructure.forcustomerslookingprice.rest.dto.response;

public class SearchPriceErrorResponse {

    private final int brandId;
    private final long productId;
    private final long applicationDate;
    private final String message;

    public SearchPriceErrorResponse(Builder builder) {
        this.brandId = builder.brandId;
        this.productId = builder.productId;
        this.applicationDate = builder.applicationDate;
        this.message = builder.message;
    }

    public int getBrandId() {
        return brandId;
    }

    public long getProductId() {
        return productId;
    }

    public long getApplicationDate() {
        return applicationDate;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {

        private int brandId;
        private long productId;
        private long applicationDate;
        private String message;

        public Builder brandId(int brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder productId(long productId) {
            this.productId = productId;
            return this;
        }

        public Builder applicationDate(long applicationDate) {
            this.applicationDate = applicationDate;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public SearchPriceErrorResponse build() {
            return new SearchPriceErrorResponse(this);
        }

    }

}
