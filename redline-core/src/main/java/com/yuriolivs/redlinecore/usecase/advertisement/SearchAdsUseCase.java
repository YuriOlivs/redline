package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.repository.AdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import com.yuriolivs.redlinecore.domain.service.ScraperClientInterface;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SearchAdsUseCase {
    private final ScraperClientInterface scraperClient;
    private final AdvertisementRepositoryInterface advertisementRepository;
    private final EventPublisherInterface eventPublisher;


    public List<Advertisement> execute(
            String brand,
            String model,
            Integer year,
            String website,
            Integer mileage,
            Integer page,
            Integer size
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
