package com.yuriolivs.redlinecore.domain.alert;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AlertPreferences {
    private boolean sendForPriceDecrease;
    private boolean sendForPriceIncrease;
    private boolean sendForDescriptionChange;
    private boolean sendForScoreChange;
    private UUID user;

    public AlertPreferences(
            UUID user
    ) {
        this.sendForPriceDecrease = true;
        this.sendForPriceIncrease = false;
        this.sendForDescriptionChange = false;
        this.sendForScoreChange = true;
        this.user = user;
    }

    public AlertPreferences(
            boolean sendForPriceDecrease,
            boolean sendForPriceIncrease,
            boolean sendForDescriptionChange,
            boolean sendForScoreChange,
            UUID user
    ) {
        validateUser(user);

        this.sendForPriceDecrease = sendForPriceDecrease;
        this.sendForPriceIncrease = sendForPriceIncrease;
        this.sendForDescriptionChange = sendForDescriptionChange;
        this.sendForScoreChange = sendForScoreChange;
        this.user = user;
    }

    void validateUser(UUID user) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");
    }

    public void setUser(UUID user) {
        validateUser(user);
        this.user = user;
    }

    public boolean isOff() {
        return !sendForPriceIncrease && !sendForDescriptionChange && !sendForPriceDecrease && !sendForScoreChange;
    }
}
