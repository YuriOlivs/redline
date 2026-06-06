package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;

import java.math.BigDecimal;

public class UpdateAdPriceUseCase {
    private AdvertisementRepositoryInterface advertisementRepository;

    public UpdateAdPriceUseCase(
            AdvertisementRepositoryInterface advertisementRepository
    ) {
        this.advertisementRepository = advertisementRepository;
    }

    public Advertisement execute(
            String url,
            BigDecimal price
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
