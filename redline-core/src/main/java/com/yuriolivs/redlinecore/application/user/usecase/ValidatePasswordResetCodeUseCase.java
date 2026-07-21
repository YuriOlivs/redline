package com.yuriolivs.redlinecore.application.user.usecase;

import com.yuriolivs.redlinecore.domain.service.ICacheService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ValidatePasswordResetCodeUseCase {
    private final ICacheService cacheService;

    public UUID execute(String code) {
        if (code == null || code.isEmpty())
            throw new IllegalArgumentException("Code can't be null");

        String stringId = cacheService.get(code);

        if (stringId != null)
            return UUID.fromString(stringId);

        return null;
    }
}
