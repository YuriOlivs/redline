package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.ScoreCalculatorInterface;

import java.math.BigDecimal;

public class UpdateAdUseCase {
    private AdvertisementRepositoryInterface advertisementRepository;
    private ScoreCalculatorInterface scoreCalculator;

    public UpdateAdUseCase(
            AdvertisementRepositoryInterface advertisementRepository,
            ScoreCalculatorInterface scoreCalculator
    ) {
        this.advertisementRepository = advertisementRepository;
        this.scoreCalculator = scoreCalculator;
    }

    public Advertisement execute(
            String url,
            Double price
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
