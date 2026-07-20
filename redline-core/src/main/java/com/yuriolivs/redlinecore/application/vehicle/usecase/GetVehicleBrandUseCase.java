package com.yuriolivs.redlinecore.application.vehicle.usecase;

import com.yuriolivs.redlinecore.domain.repository.VehicleRepositoryInterface;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetVehicleBrandUseCase {
    private final VehicleRepositoryInterface vehicleRepository;

    public List<String> execute() {
        return vehicleRepository.findAllBrands();
    }
}
