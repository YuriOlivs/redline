package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepositoryInterface {
    Vehicle save(Vehicle vehicle);
    Optional<List<Vehicle>> findAllByModel();
    Optional<List<Vehicle>> findAllByBrand();
    Optional<List<Vehicle>> findAllByYear();
    Optional<Vehicle> findByBrandAndModelAndYear(String brand, String model, Integer year);
    void remove(Vehicle vehicle);
}
