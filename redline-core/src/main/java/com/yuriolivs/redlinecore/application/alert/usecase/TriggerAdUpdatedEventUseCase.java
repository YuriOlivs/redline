package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.AlertType;
import com.yuriolivs.redlinecore.domain.event.AdUpdatedEvent;
import com.yuriolivs.redlinecore.domain.service.IEventPublisher;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TriggerAdUpdatedEventUseCase {
    private final IEventPublisher eventPublisher;

    public void execute(
            Advertisement ad,
            AlertType type
    ) {
        AdUpdatedEvent event = new AdUpdatedEvent(
                ad,
                type
        );

        eventPublisher.publish(event);
    }
}
