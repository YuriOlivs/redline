package com.yuriolivs.redlinecore.domain.event;

import java.time.LocalDateTime;

public abstract class IDomainEvent {
    private final LocalDateTime triggeredAt;

    protected IDomainEvent() {
        this.triggeredAt = LocalDateTime.now();
    }

    public LocalDateTime getTriggeredAt() {
        return this.triggeredAt;
    }
}
