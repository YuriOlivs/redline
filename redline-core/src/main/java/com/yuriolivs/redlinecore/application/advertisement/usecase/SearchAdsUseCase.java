package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperResultDto;
import com.yuriolivs.redlinecore.application.advertisement.mapper.AdvertisementMapper;
import com.yuriolivs.redlinecore.application.alert.usecase.TriggerAdsScrapedEventUseCase;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;
import com.yuriolivs.redlinecore.domain.event.AdsScrapedEvent;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import com.yuriolivs.redlinecore.domain.service.ScraperClientInterface;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class SearchAdsUseCase {
    private final ScraperClientInterface scraperClient;
    private final AdvertisementRepositoryInterface advertisementRepository;
    private final TriggerAdsScrapedEventUseCase triggerAdsScrapedEventUseCase;

    public Set<Advertisement> execute(
            AdvertisementSearchCriteria searchCriteria
    ) {
        Set<Advertisement> ads = new HashSet<>();

        List<Advertisement> databaseAds = advertisementRepository.findBySearchCriteria(searchCriteria);
        ScraperResultDto scraperResponse = scraperClient.scrapeAdsBySearchCriteria(searchCriteria);

        List<Advertisement> scraperAds = scraperResponse.advertisementDtos().stream().map(AdvertisementMapper::toDomain).toList();

        ads.addAll(databaseAds);
        ads.addAll(scraperAds);

        triggerAdsScrapedEventUseCase.execute(scraperAds);

        return ads;
    }
}
