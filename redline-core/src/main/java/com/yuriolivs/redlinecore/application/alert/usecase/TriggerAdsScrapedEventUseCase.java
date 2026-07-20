package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.event.AdsScrapedEvent;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TriggerAdsScrapedEventUseCase {
    private final EventPublisherInterface eventPublisher;

    public void execute(
            List<Advertisement> scrapedAds
    ) {
        AdsScrapedEvent event = new AdsScrapedEvent(scrapedAds);
        eventPublisher.publish(event);
    }
}
