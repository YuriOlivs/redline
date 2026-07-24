package com.yuriolivs.redlinecore.infrastructure.persistence.alert.mapper;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;
import com.yuriolivs.redlinecore.infrastructure.persistence.alert.entity.AlertPreferencesEntity;
import com.yuriolivs.redlinecore.infrastructure.persistence.user.entity.UserEntity;
import com.yuriolivs.redlinecore.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AlertPreferencesPersistenceMapper {
    public AlertPreferencesEntity toEntity(AlertPreferences alertPreferences) {
        return AlertPreferencesEntity
                .builder()
                .sendForPriceIncrease(alertPreferences.isSendForPriceIncrease())
                .sendForPriceDecrease(alertPreferences.isSendForPriceDecrease())
                .sendForScoreChange(alertPreferences.isSendForScoreChange())
                .user(
                        UserEntity
                                .builder()
                                .id(alertPreferences.getUser())
                                .build()
                )
                .build();
    }

    public AlertPreferences toDomain(AlertPreferencesEntity entity) {
        return new AlertPreferences(
                entity.isSendForPriceDecrease(),
                entity.isSendForPriceIncrease(),
                entity.isSendForDescriptionChange(),
                entity.isSendForScoreChange(),
                entity.getUser().getId()
        );
    }
}
