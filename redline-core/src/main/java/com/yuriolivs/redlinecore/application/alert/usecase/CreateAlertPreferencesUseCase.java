package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;
import com.yuriolivs.redlinecore.domain.repository.IAlertPreferencesRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class CreateAlertPreferencesUseCase {
    private final IAlertPreferencesRepository preferencesRepository;

    public AlertPreferences execute(UUID userId) {
        AlertPreferences preferences = new AlertPreferences(userId);
        return preferencesRepository.save(preferences);
    }
}
