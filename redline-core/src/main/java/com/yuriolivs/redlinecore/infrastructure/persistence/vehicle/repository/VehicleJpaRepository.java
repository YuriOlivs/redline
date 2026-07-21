package com.yuriolivs.redlinecore.infrastructure.persistence.vehicle.repository;

import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import com.yuriolivs.redlinecore.infrastructure.persistence.vehicle.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VehicleJpaRepository extends JpaRepository<VehicleEntity, UUID> {
    @Query("""
        SELECT DISTINCT v.brand
        FROM VehicleEntity v
        ORDER BY v.brand
    """)
    List<String> findAllBrands();

    @Query("""
        SELECT DISTINCT v.model
        FROM VehicleEntity v
        WHERE v.brand = :brand
        ORDER BY v.model
    """)
    List<String> findAllModelsByBrand(@Param("brand") String brand);

    @Query("""
        SELECT DISTINCT v.model
        FROM VehicleEntity v
        WHERE v.brand = :brand
          AND v.model = :model
        ORDER BY v.version
    """)
    List<String> findAllVersionsByModel(
            @Param("brand") String brand,
            @Param("model") String model
    );

    @Query("""
        SELECT DISTINCT v.year
        FROM VehicleEntity v
        WHERE v.brand = :brand
          AND v.model = :model
          AND v.version = :version
        ORDER BY v.year
    """)
    List<Integer> findAllYearsByModelAndVersion(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("version") String version
    );

    Optional<VehicleEntity> findByBrandAndModelAndYearOrderByBrand(
            String brand,
            String model,
            Integer year
    );

    Optional<VehicleEntity> findVehicle(Vehicle vehicle);
}
