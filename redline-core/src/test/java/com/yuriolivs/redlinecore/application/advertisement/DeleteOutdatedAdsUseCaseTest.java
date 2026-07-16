package com.yuriolivs.redlinecore.application.advertisement;

import com.yuriolivs.redlinecore.application.advertisement.usecase.DeleteOutdatedAdsUseCase;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteOutdatedAdsUseCaseTest {
    private AdvertisementRepositoryInterface advertisementRepository;
    private SavedAdvertisementRepositoryInterface savedAdRepository;
    private DeleteOutdatedAdsUseCase useCase;

    private SavedAdvertisement savedAdvertisement;
    private String savedAdId;

    @BeforeEach
    void setUp() {
        advertisementRepository = Mockito.mock(AdvertisementRepositoryInterface.class);
        savedAdRepository = Mockito.mock(SavedAdvertisementRepositoryInterface.class);
        useCase = new DeleteOutdatedAdsUseCase(advertisementRepository, savedAdRepository);

        savedAdId = "https://webmotors.com.br/anuncio/123";
        Advertisement advertisement = Mockito.mock(Advertisement.class);
        savedAdvertisement = Mockito.mock(SavedAdvertisement.class);

        Mockito.when(savedAdvertisement.getAdvertisement()).thenReturn(advertisement);
        Mockito.when(advertisement.getUrl()).thenReturn(savedAdId);
    }

    @Test
    void shouldDeleteOldUnsavedAdvertisementsSuccessfully() {
        String unsavedAdId = "https://webmotors.com.br/anuncio/456";
        Advertisement unsavedAdvertisement = Mockito.mock(Advertisement.class);
        Mockito.when(unsavedAdvertisement.getUrl()).thenReturn(unsavedAdId);

        Mockito.when(savedAdRepository.findAllAdvertisements())
                .thenReturn(List.of(savedAdvertisement));

        Mockito.when(advertisementRepository.findUnsavedOlderThan(
                Mockito.any(LocalDate.class)
        )).thenReturn(List.of(unsavedAdvertisement));

        assertDoesNotThrow(() -> useCase.execute());

        Mockito.verify(advertisementRepository).removeAll(List.of(unsavedAdvertisement));
    }
}