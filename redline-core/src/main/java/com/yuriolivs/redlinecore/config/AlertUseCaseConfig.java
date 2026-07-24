package com.yuriolivs.redlinecore.config;

import com.yuriolivs.redlinecore.application.alert.usecase.*;
import com.yuriolivs.redlinecore.infrastructure.messaging.producer.EventPublisher;
import com.yuriolivs.redlinecore.infrastructure.persistence.alert.repository.AlertPreferencesRepository;
import com.yuriolivs.redlinecore.infrastructure.persistence.alert.repository.AlertRepository;
import com.yuriolivs.redlinecore.infrastructure.persistence.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlertUseCaseConfig {
    @Bean
    public FindSentAlerts findSentAlerts(
            AlertRepository alertRepository
    ) {
        return new FindSentAlerts(alertRepository);
    }

    @Bean
    public SendAdUpdatedAlertUseCase sendAdUpdatedAlertUseCase(
            EventPublisher eventPublisher
    ) {
        return new SendAdUpdatedAlertUseCase(eventPublisher);
    }

    @Bean
    public TriggerAdsScrapedEventUseCase triggerAdsScrapedEventUseCase(
            EventPublisher eventPublisher
    ) {
        return new TriggerAdsScrapedEventUseCase(eventPublisher);
    }

    @Bean
    public TriggerAdUpdatedEventUseCase triggerAdUpdatedEventUseCase(
            EventPublisher eventPublisher
    ) {
        return new TriggerAdUpdatedEventUseCase(eventPublisher);
    }

    @Bean
    public UpdateAlertPreferencesUseCase updateAlertPreferencesUseCase(
            AlertPreferencesRepository alertPreferencesRepository,
            UserRepository userRepository
    ) {
        return new UpdateAlertPreferencesUseCase(
                alertPreferencesRepository,
                userRepository
        );
    }

    @Bean
    public CreateAlertPreferencesUseCase createAlertPreferencesUseCase(
            AlertPreferencesRepository alertPreferencesRepository
    ) {
        return new CreateAlertPreferencesUseCase(alertPreferencesRepository);
    }

    @Bean
    public ResetAlertPreferencesToDefaultUseCase resetAlertPreferencesToDefaultUseCase(
            UserRepository userRepository,
            AlertPreferencesRepository alertPreferencesRepository
    ) {
        return new ResetAlertPreferencesToDefaultUseCase(
                userRepository,
                alertPreferencesRepository
        );
    }
}
