package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;

import java.util.UUID;

public interface AlertPreferencesRepository {
    AlertPreferences save(AlertPreferences preferences);
    AlertPreferences findByUser(UUID userId);
}
