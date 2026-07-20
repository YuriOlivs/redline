package com.yuriolivs.redlinecore.application.vehicle.usecase;

import com.yuriolivs.redlinecore.domain.repository.IVehicleRepository;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class CheckIfVehicleExistsUseCase {
    private final IVehicleRepository vehicleRepository;

    public boolean execute(Vehicle vehicle) {
        Optional<Vehicle> vehicleFound = vehicleRepository.findVehicle(vehicle);
        return vehicleFound.isPresent();
    }
}
