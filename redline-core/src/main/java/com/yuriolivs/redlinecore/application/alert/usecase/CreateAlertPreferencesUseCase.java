package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;
import com.yuriolivs.redlinecore.domain.repository.AlertPreferencesRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateAlertPreferencesUseCase {
    private final AlertPreferencesRepository preferencesRepository;

    public AlertPreferences execute(UUID userId) {
        AlertPreferences preferences = new AlertPreferences(userId);
        return preferencesRepository.save(preferences);
    }
}
