package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperAdvertisementDto;
import com.yuriolivs.redlinecore.application.advertisement.dto.ScraperResultDto;
import com.yuriolivs.redlinecore.application.advertisement.mapper.AdvertisementMapper;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementSearchCriteria;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.event.AdsScrapedEvent;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import com.yuriolivs.redlinecore.domain.service.FIPEClientInterface;
import com.yuriolivs.redlinecore.domain.service.ScraperClientInterface;
import com.yuriolivs.redlinecore.infrastructure.ScoreCalculator;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SearchAdsUseCase {
    private final ScraperClientInterface scraperClient;
    private final EventPublisherInterface eventPublisher;

    public List<Advertisement> execute(
            AdvertisementSearchCriteria searchCriteria
    ) {
        ScraperResultDto scraperResponse = scraperClient.scrapeAdsBySearchCriteria(searchCriteria);

        if (scraperResponse.advertisementDtos().isEmpty())
            throw new NotFoundException("Advertisements");

        List<Advertisement> ads = scraperResponse.advertisementDtos().stream().map(AdvertisementMapper::toDomain).toList();
        eventPublisher.publish(new AdsScrapedEvent(ads));

        return ads;
    }
}
