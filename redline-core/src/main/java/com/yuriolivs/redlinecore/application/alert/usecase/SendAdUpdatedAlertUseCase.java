package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.event.AdUpdatedEvent;
import com.yuriolivs.redlinecore.domain.service.EventPublisherInterface;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
public class SendAdUpdatedAlertUseCase {
    private final EventPublisherInterface eventPublisher;

    public void execute(
            Alert alert,
            UUID targetUser
    ) {
        AdUpdatedEvent event = new AdUpdatedEvent(
                alert.getAdvertisement(),
                alert.getType(),
                targetUser,
                LocalDateTime.now()
        );

        eventPublisher.publish(event);
    }
}
