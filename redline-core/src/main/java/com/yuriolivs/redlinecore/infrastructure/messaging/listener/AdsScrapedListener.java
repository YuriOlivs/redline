package com.yuriolivs.redlinecore.infrastructure.messaging.listener;

import com.yuriolivs.redlinecore.domain.event.AdsScrapedEvent;
import com.yuriolivs.redlinecore.infrastructure.messaging.config.RabbitMqConfig;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdsScrapedListener {
    private final AdvertisementRepository repository;

    @RabbitListener(queues = RabbitMqConfig.ADS_SCRAPED_QUEUE)
    public void consume(AdsScrapedEvent event) {
        event.getAdvertisements().forEach(ad -> {
            try {
                repository.save(ad);
            } catch(Exception e) {
                log.error("An error occurred while trying to save the Advertisement: {}", ad.getUrl());
                log.error(e.getMessage());
            }
        });
    }
}
