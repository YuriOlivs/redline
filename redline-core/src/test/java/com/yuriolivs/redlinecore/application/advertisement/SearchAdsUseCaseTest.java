package com.yuriolivs.redlinecore.application.advertisement;

import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperAdvertisementDto;
import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperResultDto;
import com.yuriolivs.redlinecore.application.advertisement.usecase.SearchAdsUseCase;
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
    private ScraperAdvertisementDto dto;
    private ScraperResultDto resultDto;
    private AdvertisementSearchCriteria criteria;

    @BeforeEach
    void setUp() {
        scraperClient = Mockito.mock(ScraperClientInterface.class);
        advertisementRepository = Mockito.mock(AdvertisementRepositoryInterface.class);
        eventPublisher = Mockito.mock(EventPublisherInterface.class);
        useCase = new SearchAdsUseCase(scraperClient, advertisementRepository, eventPublisher);

        criteria = AdvertisementSearchCriteria.builder()
                .brand("Toyota")
                .model("Corolla")
                .year(2020)
                .website("NaPista")
                .mileage(120000)
                .page(1)
                .size(20)
                .build();

        advertisement = Mockito.mock(Advertisement.class);
        dto = Mockito.mock(ScraperAdvertisementDto.class);
        resultDto = Mockito.mock(ScraperResultDto.class);
    }

    @Test
    void shouldReturnAdsFromDatabaseWhenResultsAreFound() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of(advertisement));

        List<Advertisement> result = useCase.execute(criteria);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        Mockito.verify(advertisementRepository).findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class));
        Mockito.verify(scraperClient, Mockito.never()).scrapeAdsBySearchCriteria(Mockito.any());
    }

    @Test
    void shouldCallScraperWhenNoDatabaseResultsAreFound() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of());

        Mockito.when(scraperClient.scrapeAdsBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(resultDto);

        Mockito.when(advertisementRepository.saveAll(Mockito.anyList()))
                .thenReturn(List.of(advertisement));

        List<Advertisement> result = useCase.execute(criteria);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        Mockito.verify(scraperClient).scrapeAdsBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class));
        Mockito.verify(advertisementRepository).saveAll(Mockito.anyList());
    }

    @Test
    void shouldReturnDatabaseResultsAndPublishEventWhenResultsAreOutdated() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of(advertisement));

        Mockito.when(advertisement.isOutdated()).thenReturn(true);

        List<Advertisement> result = useCase.execute(criteria);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        Mockito.verify(eventPublisher).publish(Mockito.any());
        Mockito.verify(scraperClient, Mockito.never()).scrapeAdsBySearchCriteria(Mockito.any());
    }

    @Test
    void shouldReturnDatabaseResultsWhenScraperFails() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of(advertisement));

        Mockito.when(advertisement.isOutdated()).thenReturn(true);

        Mockito.doThrow(new RuntimeException("Scraper unavailable"))
                .when(eventPublisher).publish(Mockito.any());

        List<Advertisement> result = useCase.execute(criteria);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldReturnEmptyListWhenDatabaseIsEmptyAndScraperFails() {
        Mockito.when(advertisementRepository.findBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenReturn(List.of());

        Mockito.when(scraperClient.scrapeAdsBySearchCriteria(Mockito.any(AdvertisementSearchCriteria.class)))
                .thenThrow(new RuntimeException("Scraper unavailable"));

        List<Advertisement> result = useCase.execute(criteria);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        Mockito.verify(advertisementRepository, Mockito.never()).saveAll(Mockito.anyList());
    }
}