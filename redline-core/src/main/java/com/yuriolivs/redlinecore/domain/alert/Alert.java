package com.yuriolivs.redlinecore.domain.alert;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class Alert {
    private LocalDateTime dateTime;
    @Setter
    private AlertType type;
    @Setter
    private UUID advertisementId;
    @Setter
    private UUID userId;

    public Alert(
            AlertType type,
            UUID advertisementId,
            UUID userId,
            LocalDateTime dateTime
    ) {
        validateDateTime(dateTime);
        validateAdvertisement(advertisementId);
        validateUser(userId);

        this.type = type;
        this.advertisementId = advertisementId;
        this.userId = userId;
        this.dateTime = dateTime;
    }

    private void validateDateTime(LocalDateTime dateTime) {
        if (dateTime.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("Date Time must be past or present");
    }

    private void validateAdvertisement(UUID advertisement) {
        if (advertisement == null)
            throw new IllegalArgumentException("Advertisement can't be null");
    }

    private void validateUser(UUID user) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");
    }

    public void setDateTime(LocalDateTime dateTime) {
        validateDateTime(dateTime);
        this.dateTime = dateTime;
    }
}
