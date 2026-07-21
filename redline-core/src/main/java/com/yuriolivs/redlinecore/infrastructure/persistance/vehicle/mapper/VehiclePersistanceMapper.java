package com.yuriolivs.redlinecore.infrastructure.persistance.vehicle.mapper;

import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import com.yuriolivs.redlinecore.infrastructure.persistance.vehicle.entity.VehicleEntity;
import org.springframework.stereotype.Component;

@Component
public class VehiclePersistanceMapper {
    public VehicleEntity toEntity(Vehicle vehicle) {
        return VehicleEntity
                .builder()
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .version(vehicle.getVersion())
                .year(vehicle.getYear())
                .build();
    }

    public Vehicle toDomain(VehicleEntity entity) {
        return new Vehicle(
                entity.getBrand(),
                entity.getModel(),
                entity.getVersion(),
                entity.getYear()
        );
    }
}
