package com.yuriolivs.redlinecore.application.vehicle.usecase;

import com.yuriolivs.redlinecore.domain.repository.IVehicleRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetVehicleVersionByModel {
    private final IVehicleRepository vehicleRepository;

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
