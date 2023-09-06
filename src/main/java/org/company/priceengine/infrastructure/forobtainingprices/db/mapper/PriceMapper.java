package org.company.priceengine.infrastructure.forobtainingprices.db.mapper;

import org.company.priceengine.application.model.Price;
import org.company.priceengine.infrastructure.forobtainingprices.db.entity.PriceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    private final ModelMapper modelMapper;

    public PriceMapper() {
        this.modelMapper = new ModelMapper();
    }

    public Price toPrice(PriceEntity priceEntity) {
        return modelMapper.map(priceEntity, Price.class);
    }

}
