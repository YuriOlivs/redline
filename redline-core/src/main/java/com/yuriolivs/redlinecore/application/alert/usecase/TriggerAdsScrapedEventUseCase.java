package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.event.AdsScrapedEvent;
import com.yuriolivs.redlinecore.domain.service.IEventPublisher;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class TriggerAdsScrapedEventUseCase {
    private final IEventPublisher eventPublisher;

    public void execute(
            List<Advertisement> scrapedAds
    ) {
        AdsScrapedEvent event = new AdsScrapedEvent(
                scrapedAds
        );
        eventPublisher.publish(event);
    }
}
