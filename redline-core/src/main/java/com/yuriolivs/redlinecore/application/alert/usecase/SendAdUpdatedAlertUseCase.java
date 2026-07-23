package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.event.AdAlertTriggeredEvent;
import com.yuriolivs.redlinecore.domain.service.IEventPublisher;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class SendAdUpdatedAlertUseCase {
    private final IEventPublisher eventPublisher;

    public void execute(
            Alert alert
    ) {
        AdAlertTriggeredEvent event = new AdAlertTriggeredEvent(
                alert.getAdvertisementId(),
                alert.getType(),
                alert.getUserId()
        );

        eventPublisher.publish(event);
    }
}
