package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AlertPreferencesRepository;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ResetAlertPreferencesToDefaultUseCase {
    private final UserRepositoryInterface userRepository;
    private final AlertPreferencesRepository alertPreferencesRepository;

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
