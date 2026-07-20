package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.application.alert.usecase.TriggerAdUpdatedEventUseCase;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.alert.AlertType;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.FIPEClientInterface;
import com.yuriolivs.redlinecore.domain.service.ScoreCalculatorInterface;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UpdateAdUseCase {
    private final AdvertisementRepositoryInterface advertisementRepository;
    private final ScoreCalculatorInterface scoreCalculator;
    private final FIPEClientInterface fipeClient;
    private final TriggerAdUpdatedEventUseCase triggerAdUpdatedEventUseCase;

    public Advertisement execute(
            String url,
            Double price
    ) {
        LocalDate now = LocalDate.now();
        Optional<Advertisement> adFound = advertisementRepository.findByUrl(url);

        if (adFound.isEmpty())
            throw new NotFoundException("Advertisement not found");

        Advertisement ad = adFound.get();

        if (!ad.isActive())
            throw new IllegalArgumentException("Advertisement is inactive");

        if (!Objects.equals(ad.getPrice(), price)) {
            ad.registerPriceChange(price, now);
        }

        Vehicle vehicle = ad.getVehicle();
        Double fipeValue = fipeClient.findVehicleValue(vehicle.getBrand(), vehicle.getModel(), vehicle.getYear());

        ScoreRecord scoreRecord = scoreCalculator.calculate(ad, fipeValue, LocalDate.now());
        ad.registerScoreChange(scoreRecord);

        AlertType alertType = defineAlertType(ad, adFound.get());

        triggerAdUpdatedEventUseCase.execute(
                ad,
                alertType,
                LocalDateTime.now()
        );

        return advertisementRepository.save(ad);
    }

    private AlertType defineAlertType(
            Advertisement newVersion,
            Advertisement oldVersion
    ) {
        Double newPrice = newVersion.getPrice();
        Double oldPrice = oldVersion.getPrice();

        if (newPrice > oldPrice) {
            return AlertType.PRICE_INCREASE;
        }

        if (newPrice < oldPrice) {
            return AlertType.PRICE_REDUCTION;
        }

        int scoreDiff = Math.abs(newVersion.getScore() - oldVersion.getScore());

        if (scoreDiff >= 25) {
            return AlertType.SCORE_CHANGE;
        }

        return null;
    }
}
