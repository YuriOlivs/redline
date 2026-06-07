package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;

import java.util.UUID;

public class RemoveSavedAdUseCase {
    private SavedAdvertisementRepositoryInterface savedAdRepository;

    public RemoveSavedAdUseCase(
            SavedAdvertisementRepositoryInterface savedAdRepository
    ) {
        this.savedAdRepository = savedAdRepository;
    }

    public boolean execute(
            UUID savedAdId,
            UUID userId
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
