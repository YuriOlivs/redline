package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.AlertType;
import lombok.Getter;

@Getter
public class UpdatedAdvertisementEvent {
    private final Advertisement advertisement;
    private final AlertType type;

    public UpdatedAdvertisementEvent(
            Advertisement advertisement,
            AlertType type
    ) {
        validateAdvertisement(advertisement);
        validateType(type);

        this.advertisement = advertisement;
        this.type = type;
    }

    private void validateAdvertisement(Advertisement ad) {
        if (advertisement == null)
            throw new IllegalArgumentException("Advertisement can't be null");

        if (!advertisement.isActive())
            throw new IllegalArgumentException("Advertisement can't be inactive");
    }

    private void validateType(AlertType type) {
        if (type == null)
            throw new IllegalArgumentException("Alert type can't be null");
    }
}
