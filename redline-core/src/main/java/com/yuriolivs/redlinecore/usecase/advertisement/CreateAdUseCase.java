package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.VehicleRepositoryInterface;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;

import java.math.BigDecimal;

public class CreateAdUseCase {
    private AdvertisementRepositoryInterface advertisementRepository;
    private VehicleRepositoryInterface vehicleRepository;

    public CreateAdUseCase(
        AdvertisementRepositoryInterface advertisementRepository,
        VehicleRepositoryInterface vehicleRepository
    ) {
        this.advertisementRepository = advertisementRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Advertisement execute(
            String url,
            String website,
            String location,
            Integer mileage,
            BigDecimal price,
            Vehicle vehicle
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public Advertisement execute() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
