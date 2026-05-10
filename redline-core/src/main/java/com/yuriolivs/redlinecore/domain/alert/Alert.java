package com.yuriolivs.redlinecore.domain.alert;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Alert {
    private AlertType type;
    private Advertisement advertisement;
    private User user;
    private LocalDateTime dateTime;

    public Alert(
            AlertType type,
            Advertisement advertisement,
            User user,
            LocalDateTime dateTime
    ) {
        validateDateTime(dateTime);

        this.type = type;
        this.advertisement = advertisement;
        this.user = user;
        this.dateTime = dateTime;
    }

    private void validateDateTime(LocalDateTime dateTime) {
        if (dateTime.isAfter(LocalDateTime.now()))
            throw new IllegalArgumentException("Date Time must be past or present");
    }
}
