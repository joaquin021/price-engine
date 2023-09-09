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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataToSearchPrice that = (DataToSearchPrice) o;

        if (brandId != that.brandId) return false;
        if (productId != that.productId) return false;
        return applicationDate.equals(that.applicationDate);
    }

    @Override
    public int hashCode() {
        int result = brandId;
        result = 31 * result + (int) (productId ^ (productId >>> 32));
        result = 31 * result + applicationDate.hashCode();
        return result;
    }
}
