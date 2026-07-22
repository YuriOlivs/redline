package com.yuriolivs.redlinecore.domain.service;

import com.yuriolivs.redlinecore.domain.event.IDomainEvent;

public interface IEventPublisher {
    void publish(IDomainEvent event);
}
