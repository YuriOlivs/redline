package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.mapper;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity.SavedAdvertisementEntity;
import com.yuriolivs.redlinecore.infrastructure.persistence.user.mapper.UserPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SavedAdvertisementPersistenceMapper {
    private final AdvertisementPersistenceMapper adMapper;
    private final UserPersistenceMapper userMapper;

    public SavedAdvertisementEntity toEntity(SavedAdvertisement savedAd) {
        return SavedAdvertisementEntity
                .builder()
                .id(savedAd.getId())
                .savedDate(savedAd.getSavedDate())
                .advertisement(adMapper.toEntity(savedAd.getAdvertisement()))
                .user(userMapper.toEntity(savedAd.getUser()))
                .build();
    }

    public SavedAdvertisement toDomain(SavedAdvertisementEntity entity) {
        return new SavedAdvertisement(
                entity.getId(),
                adMapper.toDomain(entity.getAdvertisement()),
                userMapper.toDomain(entity.getUser()),
                entity.getSavedDate()
        );
    }
}
