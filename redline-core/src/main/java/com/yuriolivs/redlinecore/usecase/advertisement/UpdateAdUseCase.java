package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.FIPEClientInterface;
import com.yuriolivs.redlinecore.domain.service.ScoreCalculatorInterface;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UpdateAdUseCase {
    private final AdvertisementRepositoryInterface advertisementRepository;
    private final ScoreCalculatorInterface scoreCalculator;
    private final FIPEClientInterface fipeClient;

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

        return advertisementRepository.save(ad);
    }
}
