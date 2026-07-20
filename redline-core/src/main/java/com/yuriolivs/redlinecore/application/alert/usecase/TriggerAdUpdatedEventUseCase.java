package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.AlertType;
import com.yuriolivs.redlinecore.domain.event.AdUpdatedEvent;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class TriggerAdUpdatedEventUseCase {
    private final EventPublisherInterface eventPublisher;

    public void execute(
            Advertisement ad,
            AlertType type,
            LocalDateTime triggeredAt
    ) {
        AdUpdatedEvent event = new AdUpdatedEvent(
                ad,
                type,
                triggeredAt
        );

        eventPublisher.publish(event);
    }
}
