package com.yuriolivs.redlinecore.application.vehicle.usecase;

import com.yuriolivs.redlinecore.domain.repository.VehicleRepositoryInterface;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetVehicleYearByModelAndVersion {
    private final VehicleRepositoryInterface vehicleRepository;

    public List<Integer> execute(
            String brand,
            String model,
            String version
    ) {
        if (brand == null || brand.isEmpty() || model == null || model.isEmpty()) {
            throw new IllegalArgumentException("At least Brand and Model must be provided");
        }

        return vehicleRepository.findAllYearsByModelAndVersion(brand, model, version);
    }
}
