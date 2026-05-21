package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.service.CacheServiceInterface;

import java.util.UUID;

public class ValidatePasswordResetCodeUseCase {
    private final CacheServiceInterface cacheService;

    public ValidatePasswordResetCodeUseCase(CacheServiceInterface cacheService) {
        this.cacheService = cacheService;
    }

    UUID execute(String code) {
        if (code == null || code.isEmpty())
            throw new IllegalArgumentException("Code can't be null");

        String stringId = cacheService.get(code);

        if (stringId != null)
            return UUID.fromString(stringId);

        return null;
    }
}
