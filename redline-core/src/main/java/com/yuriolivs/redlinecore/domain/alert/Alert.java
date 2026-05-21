package com.yuriolivs.redlinecore.domain.alert;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Alert {
    private LocalDateTime dateTime;
    @Setter
    private AlertType type;
    @Setter
    private Advertisement advertisement;
    @Setter
    private User user;

    public Alert(
            AlertType type,
            Advertisement advertisement,
            User user,
            LocalDateTime dateTime
    ) {
        validateDateTime(dateTime);
        validateAdvertisement(advertisement);
        validateUser(user);

        this.type = type;
        this.advertisement = advertisement;
        this.user = user;
        this.dateTime = dateTime;
    }

    private void validateDateTime(LocalDateTime dateTime) {
        if (dateTime.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("Date Time must be past or present");
    }

    private void validateAdvertisement(Advertisement advertisement) {
        if (advertisement == null)
            throw new IllegalArgumentException("Advertisement can't be null");
    }

    private void validateUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");
    }

    public void setDateTime(LocalDateTime dateTime) {
        validateDateTime(dateTime);
        this.dateTime = dateTime;
    }
}
