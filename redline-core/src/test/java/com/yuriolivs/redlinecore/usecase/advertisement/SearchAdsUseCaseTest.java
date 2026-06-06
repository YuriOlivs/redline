package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import com.yuriolivs.redlinecore.domain.service.ScraperClientInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchAdsUseCaseTest {
    private ScraperClientInterface scraperClient;
    private AdvertisementRepositoryInterface advertisementRepository;
    private EventPublisherInterface eventPublisher;
    private SearchAdsUseCase useCase;

    private Advertisement advertisement;

    @BeforeEach
    void setUp() {
        scraperClient = Mockito.mock(ScraperClientInterface.class);
        advertisementRepository = Mockito.mock(AdvertisementRepositoryInterface.class);
        eventPublisher = Mockito.mock(EventPublisherInterface.class);
        useCase = new SearchAdsUseCase(scraperClient, advertisementRepository, eventPublisher);

        AdvertisementSearchCriteria criteria = new AdvertisementSearchCriteria(
                "Toyota",
                "Corolla",
                2020,
                null,
                null,
                0,
                20
        );

        advertisement = Mockito.mock(Advertisement.class);
    }

    @Test
    void shouldReturnAdsFromDatabaseWhenResultsAreFound() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of(advertisement));

        List<Advertisement> result = useCase.execute(
                "Toyota",
                "Corolla",
                2020,
                null,
                null,
                0,
                20
        );

        assertNotNull(result);
        assertFalse(result.isEmpty());
        Mockito.verify(advertisementRepository).findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class));
        Mockito.verify(scraperClient, Mockito.never()).findAdsBySearchCriteria(Mockito.any());
    }

    @Test
    void shouldCallScraperWhenNoDatabaseResultsAreFound() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of());

        Mockito.when(scraperClient.findAdsBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of(advertisement));

        Mockito.when(advertisementRepository.saveAll(Mockito.anyList()))
                .thenReturn(List.of(advertisement));

        List<Advertisement> result = useCase.execute(
                "Toyota",
                "Corolla",
                2020,
                null,
                null,
                0,
                20
        );

        assertNotNull(result);
        assertFalse(result.isEmpty());
        Mockito.verify(scraperClient).findAdsBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class));
        Mockito.verify(advertisementRepository).saveAll(Mockito.anyList());
    }

    @Test
    void shouldReturnDatabaseResultsAndPublishEventWhenResultsAreOutdated() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of(advertisement));

        Mockito.when(advertisement.isOutdated()).thenReturn(true);

        List<Advertisement> result = useCase.execute(
                "Toyota",
                "Corolla",
                2020,
                null,
                null,
                0,
                20
        );

        assertNotNull(result);
        assertFalse(result.isEmpty());
        Mockito.verify(eventPublisher).publish(Mockito.any());
        Mockito.verify(scraperClient, Mockito.never()).findAdsBySearchCriteria(Mockito.any());
    }

    @Test
    void shouldReturnDatabaseResultsWhenScraperFails() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of(advertisement));

        Mockito.when(advertisement.isOutdated()).thenReturn(true);

        Mockito.doThrow(new RuntimeException("Scraper unavailable"))
                .when(eventPublisher).publish(Mockito.any());

        List<Advertisement> result = useCase.execute(
                "Toyota",

                "Corolla",
                2020,
                null,
                null,
                0, 20
        );

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenDatabaseIsEmptyAndScraperFails() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of());

        Mockito.when(scraperClient.findAdsBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenThrow(new RuntimeException("Scraper unavailable"));

        List<Advertisement> result = useCase.execute(
                "Toyota",
                "Corolla",
                2020,
                null,
                null,
                0,
                20
        );

        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(advertisementRepository, Mockito.never()).saveAll(Mockito.anyList());
    }
}