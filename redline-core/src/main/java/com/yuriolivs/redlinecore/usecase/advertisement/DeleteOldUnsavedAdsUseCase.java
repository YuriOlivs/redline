package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;

public class DeleteOldUnsavedAdsUseCase {
    private AdvertisementRepositoryInterface advertisementRepository;
    private SavedAdvertisementRepositoryInterface savedAdRepository;

    public DeleteOldUnsavedAdsUseCase(
            AdvertisementRepositoryInterface advertisementRepository,
            SavedAdvertisementRepositoryInterface savedAdRepository
    ) {
        this.advertisementRepository = advertisementRepository;
        this.savedAdRepository = savedAdRepository;
    }

    public void execute() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
