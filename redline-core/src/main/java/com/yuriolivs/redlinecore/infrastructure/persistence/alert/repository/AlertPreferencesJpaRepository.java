package com.yuriolivs.redlinecore.infrastructure.persistence.alert.repository;

import com.yuriolivs.redlinecore.infrastructure.persistence.alert.entity.AlertPreferencesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface AlertPreferencesJpaRepository extends JpaRepository<AlertPreferencesEntity, UUID> {
    @Query("""
        SELECT ap
        FROM AlertPreferencesEntity ap
        WHERE ap.user.id = :userId
    """)
    Optional<AlertPreferencesEntity> findByUser(@Param("userId") UUID userId);
}
