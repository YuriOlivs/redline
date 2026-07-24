package com.yuriolivs.redlinecore.infrastructure.persistence.alert.repository;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;
import com.yuriolivs.redlinecore.domain.repository.IAlertPreferencesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AlertPreferencesRepository implements IAlertPreferencesRepository {
    private final AlertPreferencesJpaRepository jpaRepository;

    @Override
    public AlertPreferences save(AlertPreferences preferences) {
        return null;
    }

    @Override
    public AlertPreferences findByUser(UUID userId) {
        return null;
    }
}
