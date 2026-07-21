package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.repository;

import com.yuriolivs.redlinecore.domain.user.User;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity.SavedAdvertisementEntity;
import com.yuriolivs.redlinecore.infrastructure.persistence.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface SavedAdvertisementJpaRepository extends JpaRepository<SavedAdvertisementEntity, UUID> {
    List<SavedAdvertisementEntity> findByUserId(UUID userId);
    @Query("""
        SELECT DISTINCT sa.user_id
        FROM SavedAdvertisementEntity sa
        WHERE sa.advertisement.url = :url
    """)
    List<UserEntity> findUsersByAdvertisement(String url);
    @Query("""
        DELETE FROM SavedAdvertisementEntity sa
        WHERE sa.user_id = :user
    """)
    void deleteAllByUser(@Param("user") UUID userId);
}
