package org.company.priceengine.persistence.repositoty;

import org.company.priceengine.persistence.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(int brandId, long productId, Timestamp startDate, Timestamp endDate);

}
