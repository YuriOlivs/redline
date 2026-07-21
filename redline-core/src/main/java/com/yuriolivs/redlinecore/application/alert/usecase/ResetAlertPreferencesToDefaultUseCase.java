package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.IAlertPreferencesRepository;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ResetAlertPreferencesToDefaultUseCase {
    private final IUserRepository userRepository;
    private final IAlertPreferencesRepository alertPreferencesRepository;

    public AlertPreferences execute(
            UUID userId
    ) {
        boolean userExists = userRepository.findById(userId).isPresent();
        if (!userExists)
            throw new NotFoundException("User");

        AlertPreferences preferences = new AlertPreferences(userId);
        return alertPreferencesRepository.save(preferences);
    }
}
