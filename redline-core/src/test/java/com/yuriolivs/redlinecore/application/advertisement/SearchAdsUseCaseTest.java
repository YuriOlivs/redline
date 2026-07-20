package com.yuriolivs.redlinecore.application.advertisement;

import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperAdvertisementDto;
import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperResultDto;
import com.yuriolivs.redlinecore.application.advertisement.usecase.SearchAdsUseCase;
import com.yuriolivs.redlinecore.application.alert.usecase.TriggerAdsScrapedEventUseCase;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import com.yuriolivs.redlinecore.domain.service.ScraperClientInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class SearchAdsUseCaseTest {
    private ScraperClientInterface scraperClient;
    private AdvertisementRepositoryInterface advertisementRepository;
    private TriggerAdsScrapedEventUseCase triggerAdsScrapedEventUseCase;
    private SearchAdsUseCase useCase;

    private Advertisement advertisement;
    private ScraperResultDto resultDto;
    private AdvertisementSearchCriteria criteria;

    @BeforeEach
    void setUp() {
        scraperClient = Mockito.mock(ScraperClientInterface.class);
        advertisementRepository = Mockito.mock(AdvertisementRepositoryInterface.class);
        triggerAdsScrapedEventUseCase = Mockito.mock(TriggerAdsScrapedEventUseCase.class);
        useCase = new SearchAdsUseCase(scraperClient, advertisementRepository, triggerAdsScrapedEventUseCase);

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
        resultDto = Mockito.mock(ScraperResultDto.class);
    }

    @Test
    void shouldReturnMergedAdsFromDatabaseAndScraper() {
        Mockito.when(advertisementRepository.findBySearchCriteria(criteria))
                .thenReturn(List.of(advertisement));

        Mockito.when(scraperClient.scrapeAdsBySearchCriteria(criteria))
                .thenReturn(resultDto);

        Mockito.when(resultDto.advertisementDtos())
                .thenReturn(List.of());

        Set<Advertisement> result = useCase.execute(criteria);

        assertNotNull(result);
        assertFalse(result.isEmpty());

        Mockito.verify(advertisementRepository).findBySearchCriteria(criteria);
        Mockito.verify(scraperClient).scrapeAdsBySearchCriteria(criteria);
        Mockito.verify(triggerAdsScrapedEventUseCase).execute(Mockito.any());
    }

    @Test
    void shouldReturnOnlyDatabaseResultsWhenScraperFails() {
        Mockito.when(advertisementRepository.findBySearchCriteria(criteria))
                .thenReturn(List.of(advertisement));

        Mockito.when(scraperClient.scrapeAdsBySearchCriteria(criteria))
                .thenThrow(new RuntimeException("Scraper offline"));

        assertThrows(RuntimeException.class, () -> {
            useCase.execute(criteria);
        });
    }
}