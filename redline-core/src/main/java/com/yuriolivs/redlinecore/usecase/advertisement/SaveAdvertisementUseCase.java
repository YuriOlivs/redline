package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;


public class SaveAdvertisementUseCase {
    private SavedAdvertisementRepositoryInterface savedAdRepository;
    private AdvertisementRepositoryInterface advertisementRepository;

    public SaveAdvertisementUseCase(
            SavedAdvertisementRepositoryInterface savedAdRepository,
            AdvertisementRepositoryInterface advertisementRepository
    ) {
        this.savedAdRepository = savedAdRepository;
        this.advertisementRepository = advertisementRepository;
    }

    public SavedAdvertisement execute(
            String url,
            String userId
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
