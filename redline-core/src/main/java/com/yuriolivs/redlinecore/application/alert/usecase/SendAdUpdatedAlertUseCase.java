package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.event.AdAlertTriggeredEvent;
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
        AdAlertTriggeredEvent event = new AdAlertTriggeredEvent(
                alert.getAdvertisement(),
                alert.getType(),
                targetUser,
                LocalDateTime.now()
        );

        eventPublisher.publish(event);
    }
}
