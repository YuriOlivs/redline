package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.repository;

import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity.AdvertisementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdvertisementJpaRepository extends JpaRepository<AdvertisementEntity, UUID> {
    Optional<AdvertisementEntity> findByUrl(String url);

    @Query("""
        SELECT a FROM AdvertisementEntity a
        WHERE a.lastUpdate < :threshold
        AND a.id NOT IN (
            SELECT sa.advertisement.id FROM SavedAdvertisementEntity sa
        )
    """)
    List<AdvertisementEntity> findUnsavedOlderThen(@Param("threshold") LocalDate threshold);

    @Query("""
        SELECT DISTINCT a
        FROM AdvertisementEntity a
        WHERE (: brand IS NULL OR a.vehicle.brand = :brand)
          AND (:model IS NULL OR a.vehicle.model = :model)
          AND (:year IS NULL OR a.vehicle.year = :year)
          AND (:website IS NULL OR a.website = :website)
          AND (:mileage IS NULL OR a.mileage = :mileage)
    """)
    List<AdvertisementEntity> findBySearchCriteria(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("year") Integer year,
            @Param("website") String website,
            @Param("mileage") Integer mileage,
            Pageable pageable
    );
}
