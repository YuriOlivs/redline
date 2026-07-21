package com.yuriolivs.redlinecore.infrastructure.persistence.alert.mapper;

import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.infrastructure.persistence.alert.entity.AlertEntity;
import org.springframework.stereotype.Component;

@Component
public class AlertPersistenceMapper {
    public AlertEntity toEntity(Alert alert) {
        return AlertEntity
                .builder()
                .type(alert.getType())
                .dateTime(alert.getDateTime())
                .advertisementId(alert.getAdvertisementId())
                .userId(alert.getUserId())
                .build();
    }

    public Alert toDomain(AlertEntity entity) {
        return new Alert(
                entity.getType(),
                entity.getAdvertisementId(),
                entity.getUserId(),
                entity.getDateTime()
        );
    }
}
