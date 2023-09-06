package org.company.priceengine.infrastructure.forobtainingprices.db.repositoty;

import org.company.priceengine.infrastructure.forobtainingprices.db.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;
import java.util.List;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByBrandIdAndProductIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(int brandId, long productId, Timestamp startDate, Timestamp endDate);

}
