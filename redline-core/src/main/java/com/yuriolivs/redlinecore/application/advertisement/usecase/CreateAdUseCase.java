package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.repository.IAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.IVehicleRepository;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
public class CreateAdUseCase {
    private final IAdvertisementRepository advertisementRepository;
    private final IVehicleRepository vehicleRepository;

    public Advertisement execute(
            String url,
            String website,
            String location,
            Integer mileage,
            Double price,
            Vehicle vehicle
    ) {
        LocalDate now = LocalDate.now();

        if (vehicle == null)
            throw new IllegalArgumentException("Vehicle can't be null");

        boolean alreadyExists = advertisementRepository.findByUrl(url).isPresent();

        if (alreadyExists)
            throw new IllegalArgumentException("Advertisement already exists.");

        Optional<Vehicle> vehicleExists = vehicleRepository
                .findByBrandAndModelAndYear(
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getYear()
                );

        if (vehicleExists.isEmpty()) {
            vehicle = vehicleRepository.save(vehicle);
        }

        Advertisement ad = new Advertisement(
                url,
                website,
                location,
                mileage,
                vehicle
        );

        ScoreRecord scoreRecord = new ScoreRecord(0, now);

        ad.registerPriceChange(price, now);
        ad.registerScoreChange(scoreRecord);

        return advertisementRepository.save(ad);
    }
}
