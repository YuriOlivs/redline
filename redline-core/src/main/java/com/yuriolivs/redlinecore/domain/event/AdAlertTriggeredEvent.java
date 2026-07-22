package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.AlertType;

import java.time.LocalDateTime;
import java.util.UUID;

public record AdAlertTriggeredEvent(
        UUID advertisement,
        AlertType type,
        UUID targetUser,
        LocalDateTime triggeredAt
) {
    public AdAlertTriggeredEvent {
        validateAdvertisement(advertisement);
        validateTargetUser(targetUser);
        validateType(type);

    }

    private void validateAdvertisement(UUID ad) {
        if (ad == null)
            throw new IllegalArgumentException("Advertisement can't be null");
    }

    private void validateType(AlertType type) {
        if (type == null)
            throw new IllegalArgumentException("Alert type can't be null");
    }

    private void validateTargetUser(UUID targetUser) {
        if (targetUser == null) {
            throw new IllegalArgumentException("Target user can't be null");
        }
    }
}
