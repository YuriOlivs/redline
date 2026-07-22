package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.AlertType;
import lombok.*;

@Getter
@ToString
@Builder
public class AdUpdatedEvent extends DomainEvent {
    private final Advertisement ad;
    private final AlertType type;

    public AdUpdatedEvent(Advertisement ad, AlertType type) {
        validateAdvertisement(ad);
        validateType(type);

        this.ad = ad;
        this.type = type;
    }

    private void validateAdvertisement(Advertisement ad) {
        if (ad == null) {
            throw new IllegalArgumentException("Advertisement can't be null");
        }

        if (!ad.isActive()) {
            throw new IllegalArgumentException("Advertisement can't be inactive");
        }
    }

    private void validateType(AlertType type) {
        if (type == null) {
            throw new IllegalArgumentException("Alert type can't be null");
        }
    }
}