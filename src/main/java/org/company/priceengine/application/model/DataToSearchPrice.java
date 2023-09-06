package org.company.priceengine.application.model;

import java.sql.Timestamp;

public class DataToSearchPrice {

    private int brandId;
    private long productId;
    private Timestamp applicationDate;

    public DataToSearchPrice(int brandId, long productId, Timestamp applicationDate) {
        this.brandId = brandId;
        this.productId = productId;
        this.applicationDate = applicationDate;
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

    public Timestamp getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Timestamp applicationDate) {
        this.applicationDate = applicationDate;
    }
}
