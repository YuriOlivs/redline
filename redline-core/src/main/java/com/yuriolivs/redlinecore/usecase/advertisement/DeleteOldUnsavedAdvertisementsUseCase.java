package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;

public class DeleteOldUnsavedAdvertisementsUseCase {
    private AdvertisementRepositoryInterface advertisementRepository;

    public DeleteOldUnsavedAdvertisementsUseCase(
            AdvertisementRepositoryInterface advertisementRepository
    ) {
        this.advertisementRepository = advertisementRepository;
    }

    public void execute(String url) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
