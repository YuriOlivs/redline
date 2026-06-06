package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import com.yuriolivs.redlinecore.domain.service.ScraperClientInterface;

import java.util.List;

public class SearchAdsUseCase {
    private ScraperClientInterface scraperClient;
    private AdvertisementRepositoryInterface advertisementRepository;
    private EventPublisherInterface eventPublisher;

    public SearchAdsUseCase(
            ScraperClientInterface scraperClient,
            AdvertisementRepositoryInterface advertisementRepository,
            EventPublisherInterface eventPublisher
    ) {
        this.scraperClient = scraperClient;
        this.advertisementRepository = advertisementRepository;
        this.eventPublisher = eventPublisher;
    }

    public List<Advertisement> execute(
            String brand,
            String model,
            Integer year,
            String website,
            Integer mileage
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
