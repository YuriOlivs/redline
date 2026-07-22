package com.yuriolivs.redlinecore.infrastructure.messaging.producer;

import com.yuriolivs.redlinecore.domain.event.AdAlertTriggeredEvent;
import com.yuriolivs.redlinecore.domain.event.AdUpdatedEvent;
import com.yuriolivs.redlinecore.domain.event.AdsScrapedEvent;
import com.yuriolivs.redlinecore.domain.event.DomainEvent;
import com.yuriolivs.redlinecore.domain.service.IEventPublisher;

public class EventPublisher implements IEventPublisher {
    @Override
    public void publish(DomainEvent event) {
        if (event instanceof AdUpdatedEvent) {

        }

        if (event instanceof AdsScrapedEvent) {

        }

        if (event instanceof AdAlertTriggeredEvent) {

        }
    }
}
