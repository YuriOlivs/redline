package com.yuriolivs.redlinecore.domain.service;

import com.yuriolivs.redlinecore.domain.event.DomainEvent;

public interface IEventPublisher {
    void publish(DomainEvent event);
}
