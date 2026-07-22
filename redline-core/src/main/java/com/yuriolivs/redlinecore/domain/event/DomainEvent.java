package com.yuriolivs.redlinecore.domain.event;

import java.time.LocalDateTime;

public abstract class DomainEvent {
    private final LocalDateTime triggeredAt;

    protected DomainEvent() {
        this.triggeredAt = LocalDateTime.now();
    }

    public LocalDateTime getTriggeredAt() {
        return this.triggeredAt;
    }
}
