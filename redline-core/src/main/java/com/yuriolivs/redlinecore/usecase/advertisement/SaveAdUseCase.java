package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;


public class SaveAdUseCase {
    private SavedAdvertisementRepositoryInterface savedAdRepository;
    private AdvertisementRepositoryInterface advertisementRepository;
    private UserRepositoryInterface userRepository;

    public SaveAdUseCase(
            SavedAdvertisementRepositoryInterface savedAdRepository,
            AdvertisementRepositoryInterface advertisementRepository,
            UserRepositoryInterface userRepository
    ) {
        this.savedAdRepository = savedAdRepository;
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    public SavedAdvertisement execute(
            String url,
            String userId
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
