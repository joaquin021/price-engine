package org.company.priceengine.application.model;

import java.math.BigDecimal;

public class Price {

    private int brandId;
    private long productId;
    private int priority;
    private BigDecimal price;
    private String currency;

    public Price() {
    }

    public Price(Builder builder) {
        this.brandId = builder.brandId;
        this.productId = builder.productId;
        this.priority = builder.priority;
        this.price = builder.price;
        this.currency = builder.currency;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public static class Builder {

        private int brandId;
        private long productId;
        private int priority;
        private BigDecimal price;
        private String currency;

        public Builder brandId(int brandId) {
            this.brandId = brandId;
            return this;
        }

        public Builder productId(long productId) {
            this.productId = productId;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public Price build() {
            return new Price(this);
        }

    }

}
