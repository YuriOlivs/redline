package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;

import java.util.UUID;

public interface IAlertPreferencesRepository {
    AlertPreferences save(AlertPreferences preferences);
    AlertPreferences findByUser(UUID userId);
}
