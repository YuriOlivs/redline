package com.yuriolivs.redlinecore.config;

import com.yuriolivs.redlinecore.application.advertisement.usecase.*;
import com.yuriolivs.redlinecore.application.alert.usecase.TriggerAdUpdatedEventUseCase;
import com.yuriolivs.redlinecore.application.alert.usecase.TriggerAdsScrapedEventUseCase;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.repository.AdvertisementRepository;
import com.yuriolivs.redlinecore.infrastructure.persistence.user.repository.UserRepository;
import com.yuriolivs.redlinecore.infrastructure.service.FipeClient;
import com.yuriolivs.redlinecore.infrastructure.service.ScoreCalculator;
import com.yuriolivs.redlinecore.infrastructure.service.ScraperClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AdvertisementUseCaseConfig {

    @Bean
    public GetSpecificAdUseCase getSpecificAdUseCase(
            AdvertisementRepository advertisementRepository
    ) {
        return new GetSpecificAdUseCase(
                advertisementRepository
        );
    }

    @Bean
    public DeleteOutdatedAdsUseCase deleteOutdatedAdsUseCase(
            AdvertisementRepository advertisementRepository

    ) {
        return new DeleteOutdatedAdsUseCase(
                advertisementRepository
        );
    }

    @Bean
    public GetUsersThatSavedAdUseCase getUsersThatSavedByAdvertisementUseCase (
            AdvertisementRepository advertisementRepository
    ) {
        return new GetUsersThatSavedAdUseCase(
                advertisementRepository,
                advertisementRepository
        );
    }

    @Bean
    public SaveAdUseCase saveAdUseCase(
            AdvertisementRepository advertisementRepository,
            UserRepository userRepository
    ) {
        return new SaveAdUseCase(
                advertisementRepository,
                advertisementRepository,
                userRepository
        );
    }

    @Bean
    public SearchAdsUseCase searchAdsUseCase(
            ScraperClient scraperClient,
            AdvertisementRepository advertisementRepository,
            TriggerAdsScrapedEventUseCase triggerAdsScrapedEventUseCase
    ) {
        return new SearchAdsUseCase(
                scraperClient,
                advertisementRepository,
                triggerAdsScrapedEventUseCase
        );
    }

    @Bean
    public UpdateAdUseCase useCase(
            AdvertisementRepository advertisementRepository,
            ScoreCalculator scoreCalculator,
            FipeClient fipeClient,
            TriggerAdUpdatedEventUseCase triggerAdUpdatedEventUseCase
    ) {
        return new UpdateAdUseCase(
                advertisementRepository,
                scoreCalculator,
                fipeClient,
                triggerAdUpdatedEventUseCase
        );
    }
}
