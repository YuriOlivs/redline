package com.yuriolivs.redlinecore.infrastructure.persistance.alert.repository;

import com.yuriolivs.redlinecore.domain.alert.AlertType;
import com.yuriolivs.redlinecore.infrastructure.persistance.alert.entity.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface AlertJpaRepository extends JpaRepository<AlertEntity, UUID> {
    @Query("""
        SELECT a
        FROM AlertEntity a
        WHERE (:type IS NULL OR a.type = :type)
          AND (:advertisementId IS NULL OR a.advertisementId = :advertisementId)
          AND (:userId IS NULL OR a.userId = :userId)
          AND (:from IS NULL OR a.dateTime >= :from)
          AND (:to IS NULL OR a.dateTime <= :to)
    """)
    List<AlertEntity> findBySearchCriteria(
            @Param("type") AlertType type,
            @Param("advertisement") UUID advertisementId,
            @Param("user") UUID userId,
            @Param("from") LocalDateTime from,
            @Param("to") LocalDateTime to
    );
}
