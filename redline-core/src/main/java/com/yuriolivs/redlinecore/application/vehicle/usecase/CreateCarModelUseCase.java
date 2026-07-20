package com.yuriolivs.redlinecore.application.vehicle.usecase;

import com.yuriolivs.redlinecore.domain.repository.VehicleRepositoryInterface;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateCarModelUseCase {
    private final VehicleRepositoryInterface vehicleRepository;

    public Vehicle execute(
            String brand,
            String model,
            String version,
            Integer year
    ) {
        Vehicle vehicle = new Vehicle(
                brand,
                model,
                version,
                year
        );

        return vehicleRepository.save(vehicle);
    }
}
