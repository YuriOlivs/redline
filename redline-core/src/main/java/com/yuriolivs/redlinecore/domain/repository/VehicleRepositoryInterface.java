package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleRepositoryInterface {
    Vehicle save(Vehicle vehicle);
    List<String> findAllBrands();
    List<String> findAllModelsByBrand(String brand);
    List<String> findAllVersionsByModel(String brand, String model);
    List<Integer> findAllYearsByModelAndVersion(String brand, String model, String version);
    Optional<Vehicle> findByBrandAndModelAndYear(String brand, String model, Integer year);
    Optional<Vehicle> findVehicle(Vehicle vehicle);
    void remove(Vehicle vehicle);
}
