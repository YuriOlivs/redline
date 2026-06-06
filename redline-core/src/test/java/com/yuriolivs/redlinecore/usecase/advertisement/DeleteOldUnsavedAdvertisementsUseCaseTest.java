package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteOldUnsavedAdvertisementsUseCaseTest {
    private AdvertisementRepositoryInterface advertisementRepository;
    private SavedAdvertisementRepositoryInterface savedAdRepository;
    private DeleteOldUnsavedAdvertisementsUseCase useCase;

    private Advertisement advertisement;
    private SavedAdvertisement savedAdvertisement;
    private UUID savedAdId;

    @BeforeEach
    void setUp() {
        advertisementRepository = Mockito.mock(AdvertisementRepositoryInterface.class);
        savedAdRepository = Mockito.mock(SavedAdvertisementRepositoryInterface.class);
        useCase = new DeleteOldUnsavedAdvertisementsUseCase(advertisementRepository, savedAdRepository);

        savedAdId = UUID.randomUUID();
        advertisement = Mockito.mock(Advertisement.class);
        savedAdvertisement = Mockito.mock(SavedAdvertisement.class);

        Mockito.when(savedAdvertisement.getAdvertisement()).thenReturn(advertisement);
        Mockito.when(advertisement.getId()).thenReturn(savedAdId);
    }

    @Test
    void shouldDeleteOldUnsavedAdvertisementsSuccessfully() {
        UUID unsavedAdId = UUID.randomUUID();
        Advertisement unsavedAdvertisement = Mockito.mock(Advertisement.class);
        Mockito.when(unsavedAdvertisement.getId()).thenReturn(unsavedAdId);

        Mockito.when(savedAdRepository.findAllAdvertisements())
                .thenReturn(List.of(savedAdvertisement));

        Mockito.when(advertisementRepository.findUnsavedOlderThan(
                Mockito.any(LocalDate.class),
                Mockito.eq(List.of(savedAdId))
        )).thenReturn(List.of(unsavedAdvertisement));

        assertDoesNotThrow(() -> useCase.execute());

        Mockito.verify(advertisementRepository).remove(unsavedAdvertisement);
    }

    @Test
    void shouldLogErrorWhenExceptionOccurs() {
        // TODO: definir estratégia de log antes de implementar
    }
}