package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.AlertType;

import java.time.LocalDateTime;

public record AdUpdatedEvent(
        Advertisement ad,
        AlertType type,
        LocalDateTime triggeredAt
) {
    public AdUpdatedEvent {
        validateAdvertisement(ad);
        validateType(type);
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
}
