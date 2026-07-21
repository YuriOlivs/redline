package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.mapper;

import com.yuriolivs.redlinecore.domain.advertisement.PriceRecord;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity.PriceRecordEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PricePersistenceMapper {
    public PriceRecordEntity toEntity(PriceRecord record) {
        return PriceRecordEntity
                .builder()
                .id(UUID.randomUUID())
                .price(record.getPrice())
                .date(record.getDate())
                .build();
    }

    public PriceRecord toDomain(PriceRecordEntity entity) {
        return new PriceRecord(
                entity.getPrice(),
                entity.getDate()
        );
    }
}
