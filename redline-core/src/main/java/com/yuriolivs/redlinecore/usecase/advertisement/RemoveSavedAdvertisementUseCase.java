package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;

public class RemoveSavedAdvertisementUseCase {
    private SavedAdvertisementRepositoryInterface savedAdRepository;

    public RemoveSavedAdvertisementUseCase(
            SavedAdvertisementRepositoryInterface savedAdRepository
    ) {
        this.savedAdRepository = savedAdRepository;
    }

    public boolean execute() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
