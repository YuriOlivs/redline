package com.yuriolivs.redlinecore.application.vehicle.usecase;

import com.yuriolivs.redlinecore.domain.repository.VehicleRepositoryInterface;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetVehicleModelByBrandUseCase {
    private final VehicleRepositoryInterface vehicleRepository;

    public List<String> execute(String brand) {
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException("Brand can't be null or empty");
        }

        return vehicleRepository.findAllModelsByBrand(brand);
    }
}
