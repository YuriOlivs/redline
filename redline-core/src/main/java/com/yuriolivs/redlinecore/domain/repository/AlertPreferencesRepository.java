package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;

public interface AlertPreferencesRepository {
    AlertPreferences save(AlertPreferences preferences);
}
