package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.service.CacheServiceInterface;

import java.util.UUID;

public class ValidatePasswordResetCodeUseCase {
    private final CacheServiceInterface cacheService;

    public ValidatePasswordResetCodeUseCase(CacheServiceInterface cacheService) {
        this.cacheService = cacheService;
    }

    UUID execute(String code) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
