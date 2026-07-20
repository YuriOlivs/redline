package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.IAlertPreferencesRepository;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateAlertPreferencesUseCase {
    private final IAlertPreferencesRepository alertPreferencesRepository;
    private final IUserRepository userRepository;

    public AlertPreferences execute(
            UUID userId,
            boolean priceDecrease,
            boolean priceIncrease,
            boolean priceDescription,
            boolean scoreChange
    ) {
        boolean userExists = userRepository.findById(userId).isPresent();
        if (!userExists)
            throw new NotFoundException("User");

        AlertPreferences preferences = alertPreferencesRepository.findByUser(userId);

        preferences.setSendForPriceDecrease(priceDecrease);
        preferences.setSendForPriceIncrease(priceIncrease);
        preferences.setSendForDescriptionChange(priceDescription);
        preferences.setSendForScoreChange(scoreChange);

        return alertPreferencesRepository.save(preferences);
    }
}
