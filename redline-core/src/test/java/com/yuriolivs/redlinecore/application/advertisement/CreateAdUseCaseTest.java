package com.yuriolivs.redlinecore.application.advertisement;

import com.yuriolivs.redlinecore.application.advertisement.usecase.CreateAdUseCase;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.VehicleRepositoryInterface;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CreateAdUseCaseTest {
    private AdvertisementRepositoryInterface advertisementRepository;
    private VehicleRepositoryInterface vehicleRepository;
    private CreateAdUseCase useCase;

    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        advertisementRepository = Mockito.mock(AdvertisementRepositoryInterface.class);
        vehicleRepository = Mockito.mock(VehicleRepositoryInterface.class);
        useCase = new CreateAdUseCase(advertisementRepository, vehicleRepository);

        vehicle = new Vehicle("Toyota", "Corolla", 2020);
    }

    @Test
    void shouldCreateAdvertisementSuccessfullyWithExistingVehicle() {
        Mockito.when(vehicleRepository.findByBrandAndModelAndYear("Toyota", "Corolla", 2020))
                .thenReturn(Optional.of(vehicle));

        Mockito.when(advertisementRepository.save(Mockito.any(Advertisement.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Advertisement result = useCase.execute(
                "https://webmotors.com.br/anuncio/123",
                "Webmotors",
                "São Paulo",
                45000,
                85000.00,
                vehicle
        );

        assertNotNull(result);
        Mockito.verify(vehicleRepository).findByBrandAndModelAndYear("Toyota", "Corolla", 2020);
        Mockito.verify(vehicleRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(advertisementRepository).save(Mockito.any(Advertisement.class));
    }

    @Test
    void shouldCreateAdvertisementSuccessfullyWithNewVehicle() {
        Mockito.when(vehicleRepository.findByBrandAndModelAndYear("Toyota", "Corolla", 2020))
                .thenReturn(Optional.empty());

        Mockito.when(vehicleRepository.save(vehicle))
                .thenReturn(vehicle);

        Mockito.when(advertisementRepository.save(Mockito.any(Advertisement.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Advertisement result = useCase.execute(
                "https://webmotors.com.br/anuncio/123",
                "Webmotors",
                "São Paulo",
                45000,
                85000.00,
                vehicle
        );

        assertNotNull(result);
        Mockito.verify(vehicleRepository).findByBrandAndModelAndYear("Toyota", "Corolla", 2020);
        Mockito.verify(vehicleRepository).save(vehicle);
        Mockito.verify(advertisementRepository).save(Mockito.any(Advertisement.class));
    }

    @Test
    void shouldThrowExceptionWhenVehicleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> useCase.execute(
                "https://webmotors.com.br/anuncio/123",
                "Webmotors",
                "São Paulo",
                45000,
                85000.00,
                null
        ));

        Mockito.verify(advertisementRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(vehicleRepository, Mockito.never()).save(Mockito.any());
    }
}