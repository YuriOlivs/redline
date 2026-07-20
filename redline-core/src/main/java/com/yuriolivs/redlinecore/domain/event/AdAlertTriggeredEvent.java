package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.AlertType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class AdAlertTriggeredEvent {
    private final Advertisement advertisement;
    private final AlertType type;
    private final LocalDateTime triggeredAt;
    private final UUID targetUser;

    public AdAlertTriggeredEvent(
            Advertisement advertisement,
            AlertType type,
            UUID targetUser,
            LocalDateTime triggeredAt
    ) {
        validateAdvertisement(advertisement);
        validateTargetUser(targetUser);
        validateType(type);

        this.advertisement = advertisement;
        this.type = type;
        this.targetUser = targetUser;
        this.triggeredAt = triggeredAt;
    }

    private void validateAdvertisement(Advertisement ad) {
        if (ad == null)
            throw new IllegalArgumentException("Advertisement can't be null");

        if (!ad.isActive())
            throw new IllegalArgumentException("Advertisement can't be inactive");
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
