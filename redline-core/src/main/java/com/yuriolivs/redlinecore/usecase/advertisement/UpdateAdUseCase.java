package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.ScoreCalculatorInterface;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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
        LocalDate now = LocalDate.now();
        Optional<Advertisement> adFound = advertisementRepository.findByUrl(url);

        if (adFound.isEmpty())
            throw new NotFoundException("Advertisement not found");

        Advertisement ad = adFound.get();

        ad.registerPriceChange(price, now);
        ScoreRecord scoreRecord = scoreCalculator.calculate(ad);

        ad.registerScoreChange(scoreRecord);

        return advertisementRepository.save(ad);
    }
}
