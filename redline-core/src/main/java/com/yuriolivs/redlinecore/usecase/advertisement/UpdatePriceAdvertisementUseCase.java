package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;

import java.math.BigDecimal;

public class UpdatePriceAdvertisementUseCase {
    private AdvertisementRepositoryInterface advertisementRepository;

    public UpdatePriceAdvertisementUseCase(
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
