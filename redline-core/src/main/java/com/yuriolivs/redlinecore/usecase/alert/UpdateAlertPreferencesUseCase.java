package com.yuriolivs.redlinecore.usecase.alert;

import com.yuriolivs.redlinecore.domain.alert.AlertPreferences;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AlertPreferencesRepository;
import com.yuriolivs.redlinecore.domain.repository.AlertRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateAlertPreferencesUseCase {
    private final AlertPreferencesRepository alertPreferencesRepository;
    private final UserRepositoryInterface userRepository;

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
