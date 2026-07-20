package com.yuriolivs.redlinecore.application.vehicle.usecase;

import com.yuriolivs.redlinecore.domain.repository.VehicleRepositoryInterface;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetVehicleVersionByModel {
    private final VehicleRepositoryInterface vehicleRepository;

    public List<String> execute(
            String brand,
            String model
    ) {
        if (brand == null || brand.isEmpty() || model == null || model.isEmpty()) {
            throw new IllegalArgumentException("Brand and Model must be provided");
        }

        return vehicleRepository.findAllVersionsByModel(brand, model);
    }
}
