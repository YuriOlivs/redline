package com.yuriolivs.redlinecore.domain.event;

import com.yuriolivs.redlinecore.domain.alert.AlertType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Builder
@ToString
public class AdAlertTriggeredEvent extends DomainEvent {
    private final UUID advertisement;
    private final AlertType type;
    private final UUID targetUser;

    public AdAlertTriggeredEvent(
            UUID advertisement,
            AlertType type,
            UUID targetUser
    ) {
        validateAdvertisement(advertisement);
        validateTargetUser(targetUser);
        validateType(type);

        this.advertisement = advertisement;
        this.type = type;
        this.targetUser = targetUser;
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
