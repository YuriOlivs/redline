package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.mapper;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity.AdvertisementEntity;
import com.yuriolivs.redlinecore.infrastructure.persistence.vehicle.mapper.VehiclePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdvertisementPersistenceMapper {
    private final VehiclePersistenceMapper vehicleMapper;
    private final PricePersistenceMapper priceMapper;
    private final ScorePersistenceMapper scoreMapper;

    public AdvertisementEntity toEntity(Advertisement advertisement) {
        AdvertisementEntity entity = AdvertisementEntity
                .builder()
                .id(advertisement.getId())
                .url(advertisement.getUrl())
                .website(advertisement.getWebsite())
                .location(advertisement.getLocation())
                .mileage(advertisement.getMileage())
                .lastUpdate(advertisement.getLastUpdate())
                .createdAt(advertisement.getCreatedAt())
                .active(advertisement.isActive())
                .build();

        advertisement.getPriceHistory()
                .stream()
                .map(priceMapper::toEntity)
                .forEach(price -> {
                    price.setAdvertisement(entity);
                    entity.registerPriceChange(price);
                });

        advertisement.getScoreHistory()
                .stream()
                .map(scoreMapper::toEntity)
                .forEach(score -> {
                    score.setAdvertisement(entity);
                    entity.registerScoreChange(score);
                });

        return entity;
    }

    public Advertisement toDomain(AdvertisementEntity entity) {
        return new Advertisement(
                entity.getId(),
                entity.getUrl(),
                entity.getWebsite(),
                entity.getLocation(),
                entity.getMileage(),
                vehicleMapper.toDomain(entity.getVehicle()),
                entity.getPriceHistory()
                        .stream()
                        .map(priceMapper::toDomain)
                        .toList(),
                entity.getLastUpdate(),
                entity.getScoreHistory()
                        .stream()
                        .map(scoreMapper::toDomain)
                        .toList(),
                entity.getCreatedAt()
        );
    }
}
