package com.yuriolivs.redlinecore.infrastructure.messaging.producer;

import com.yuriolivs.redlinecore.domain.event.IDomainEvent;
import com.yuriolivs.redlinecore.domain.service.IEventPublisher;

public class EventPublisher implements IEventPublisher {
    @Override
    public void publish(IDomainEvent event) {

    }
}
