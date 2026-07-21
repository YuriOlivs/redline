package com.yuriolivs.redlinecore.infrastructure.persistence.vehicle.repository;

import com.yuriolivs.redlinecore.domain.repository.IVehicleRepository;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import com.yuriolivs.redlinecore.infrastructure.persistence.vehicle.entity.VehicleEntity;
import com.yuriolivs.redlinecore.infrastructure.persistence.vehicle.mapper.VehiclePersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class VehicleRepository implements IVehicleRepository {
    private final VehicleJpaRepository jpaRepository;
    private final VehiclePersistenceMapper mapper;

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleEntity vehicleSaved = jpaRepository.save(mapper.toEntity(vehicle));
        return mapper.toDomain(vehicleSaved);
    }

    @Override
    public List<String> findAllBrands() {
        return jpaRepository.findAllBrands();
    }

    @Override
    public List<String> findAllModelsByBrand(String brand) {
        return jpaRepository.findAllModelsByBrand(brand);
    }

    @Override
    public List<String> findAllVersionsByModel(String brand, String model) {
        return jpaRepository.findAllVersionsByModel(brand, model);
    }

    @Override
    public List<Integer> findAllYearsByModelAndVersion(String brand, String model, String version) {
        return jpaRepository.findAllYearsByModelAndVersion(brand, model, version);
    }

    @Override
    public Optional<Vehicle> findByBrandAndModelAndYear(String brand, String model, Integer year) {
        return jpaRepository.findByBrandAndModelAndYearOrderByBrand(brand, model, year).map(mapper::toDomain);
    }

    @Override
    public Optional<Vehicle> findVehicle(Vehicle vehicle) {
        return jpaRepository.findVehicle(vehicle).map(mapper::toDomain);
    }

    @Override
    public void remove(Vehicle vehicle) {
        jpaRepository.delete(mapper.toEntity(vehicle));
    }
}
