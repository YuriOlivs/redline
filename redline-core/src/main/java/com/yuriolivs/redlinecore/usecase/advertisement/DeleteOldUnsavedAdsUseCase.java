package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DeleteOldUnsavedAdsUseCase {
    private final AdvertisementRepositoryInterface advertisementRepository;
    private final SavedAdvertisementRepositoryInterface savedAdRepository;

    public void execute() {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
