package com.yuriolivs.redlinecore.application.vehicle.usecase;

import com.yuriolivs.redlinecore.domain.repository.IVehicleRepository;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CreateCarModelUseCase {
    private final IVehicleRepository vehicleRepository;

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

        Optional<Vehicle> vehicleFound = vehicleRepository.findVehicle(vehicle);
        return vehicleFound.orElseGet(() -> vehicleRepository.save(vehicle));
    }
}
