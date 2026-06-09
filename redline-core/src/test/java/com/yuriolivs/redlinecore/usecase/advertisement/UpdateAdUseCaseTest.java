package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.FIPEClientInterface;
import com.yuriolivs.redlinecore.domain.service.ScoreCalculatorInterface;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateAdUseCaseTest {
    private AdvertisementRepositoryInterface advertisementRepository;
    private ScoreCalculatorInterface scoreCalculator;
    private FIPEClientInterface fipeClient;
    private UpdateAdUseCase useCase;

    private Advertisement advertisement;
    private ScoreRecord scoreRecord;
    private Vehicle vehicle;

    @BeforeEach
    void setUp() {
        advertisementRepository = Mockito.mock(AdvertisementRepositoryInterface.class);
        scoreCalculator = Mockito.mock(ScoreCalculatorInterface.class);
        fipeClient = Mockito.mock(FIPEClientInterface.class);
        useCase = new UpdateAdUseCase(advertisementRepository, scoreCalculator, fipeClient);

        advertisement = Mockito.mock(Advertisement.class);
        scoreRecord = Mockito.mock(ScoreRecord.class);
        vehicle = Mockito.mock(Vehicle.class);

        Mockito.when(advertisement.getVehicle()).thenReturn(vehicle);
        Mockito.when(vehicle.getBrand()).thenReturn("Toyota");
        Mockito.when(vehicle.getModel()).thenReturn("Corolla");
        Mockito.when(vehicle.getYear()).thenReturn(2020);
        Mockito.when(fipeClient.findVehicleValue("Toyota", "Corolla", 2020)).thenReturn(80000.0);
        Mockito.when(scoreCalculator.calculate(advertisement, 80000.0)).thenReturn(scoreRecord);
        Mockito.when(advertisementRepository.save(Mockito.any(Advertisement.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void shouldUpdateAdSuccessfullyWithPriceChange() {
        Double newPrice = 85000.0;
        Double currentPrice = 90000.0;

        Mockito.when(advertisementRepository.findByUrl("https://webmotors.com.br/anuncio/123"))
                .thenReturn(Optional.of(advertisement));

        Mockito.when(advertisement.getPrice()).thenReturn(currentPrice);

        Advertisement result = useCase.execute("https://webmotors.com.br/anuncio/123", newPrice);

        assertNotNull(result);
        Mockito.verify(advertisement).registerPriceChange(Mockito.eq(newPrice), Mockito.any());
        Mockito.verify(fipeClient).findVehicleValue("Toyota", "Corolla", 2020);
        Mockito.verify(scoreCalculator).calculate(advertisement, 80000.0);
        Mockito.verify(advertisement).registerScoreChange(scoreRecord);
        Mockito.verify(advertisementRepository).save(advertisement);
    }

    @Test
    void shouldUpdateAdSuccessfullyWithoutPriceChange() {
        Double samePrice = 85000.0;

        Mockito.when(advertisementRepository.findByUrl("https://webmotors.com.br/anuncio/123"))
                .thenReturn(Optional.of(advertisement));

        Mockito.when(advertisement.getPrice()).thenReturn(samePrice);

        Advertisement result = useCase.execute("https://webmotors.com.br/anuncio/123", samePrice);

        assertNotNull(result);
        Mockito.verify(advertisement, Mockito.never()).registerPriceChange(Mockito.any(), Mockito.any());
        Mockito.verify(fipeClient).findVehicleValue("Toyota", "Corolla", 2020);
        Mockito.verify(scoreCalculator).calculate(advertisement, 80000.0);
        Mockito.verify(advertisement).registerScoreChange(scoreRecord);
        Mockito.verify(advertisementRepository).save(advertisement);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenAdvertisementDoesNotExist() {
        Mockito.when(advertisementRepository.findByUrl("https://webmotors.com.br/anuncio/123"))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                useCase.execute("https://webmotors.com.br/anuncio/123", 85000.0)
        );

        Mockito.verify(fipeClient, Mockito.never()).findVehicleValue(Mockito.any(), Mockito.any(), Mockito.any());
        Mockito.verify(scoreCalculator, Mockito.never()).calculate(Mockito.any(), Mockito.any());
        Mockito.verify(advertisementRepository, Mockito.never()).save(Mockito.any());
    }
}