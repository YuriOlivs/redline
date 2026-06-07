package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.ScoreCalculatorInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateAdUseCaseTest {
    private AdvertisementRepositoryInterface advertisementRepository;
    private ScoreCalculatorInterface scoreCalculator;
    private UpdateAdUseCase useCase;

    private Advertisement advertisement;
    private ScoreRecord scoreRecord;

    @BeforeEach
    void setUp() {
        advertisementRepository = Mockito.mock(AdvertisementRepositoryInterface.class);
        scoreCalculator = Mockito.mock(ScoreCalculatorInterface.class);
        useCase = new UpdateAdUseCase(advertisementRepository, scoreCalculator);

        advertisement = Mockito.mock(Advertisement.class);
        scoreRecord = Mockito.mock(ScoreRecord.class);

        Mockito.when(scoreCalculator.calculate(advertisement)).thenReturn(scoreRecord);
        Mockito.when(advertisementRepository.save(Mockito.any(Advertisement.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void shouldUpdateAdSuccessfullyWithPriceChange() {
        Double newPrice = 85000.0;

        Mockito.when(advertisementRepository.findByUrl("https://webmotors.com.br/anuncio/123"))
                .thenReturn(Optional.of(advertisement));

        Mockito.when(advertisement.getPrice()).thenReturn(new BigDecimal("90000.00"));

        Advertisement result = useCase.execute("https://webmotors.com.br/anuncio/123", newPrice);

        assertNotNull(result);
        Mockito.verify(advertisement).registerPriceChange(newPrice, LocalDate.now());
        Mockito.verify(scoreCalculator).calculate(advertisement);
        Mockito.verify(advertisement).registerScoreChange(scoreRecord);
        Mockito.verify(advertisementRepository).save(advertisement);
    }

    @Test
    void shouldUpdateAdSuccessfullyWithoutPriceChange() {
        double samePrice = 85000.00;

        Mockito.when(advertisementRepository.findByUrl("https://webmotors.com.br/anuncio/123"))
                .thenReturn(Optional.of(advertisement));

        Mockito.when(advertisement.getPrice()).thenReturn(BigDecimal.valueOf(samePrice));

        Advertisement result = useCase.execute("https://webmotors.com.br/anuncio/123", samePrice);

        assertNotNull(result);
        Mockito.verify(advertisement, Mockito.never()).registerPriceChange(Mockito.any(), LocalDate.now());
        Mockito.verify(scoreCalculator).calculate(advertisement);
        Mockito.verify(advertisement).registerScoreChange(scoreRecord);
        Mockito.verify(advertisementRepository).save(advertisement);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenAdvertisementDoesNotExist() {
        Mockito.when(advertisementRepository.findByUrl("https://webmotors.com.br/anuncio/123"))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->
                useCase.execute("https://webmotors.com.br/anuncio/123", 85000.00)
        );

        Mockito.verify(scoreCalculator, Mockito.never()).calculate(Mockito.any());
        Mockito.verify(advertisementRepository, Mockito.never()).save(Mockito.any());
    }
}