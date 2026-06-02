package com.yuriolivs.redlinecore.domain.alert;


import com.yuriolivs.redlinecore.domain.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlertPreferences {
    private boolean sendForPriceDecrease;
    private boolean sendForPriceIncrease;
    private boolean sendForDescriptionChange;
    private boolean sendForScoreChange;
    private User user;

    public AlertPreferences(
            boolean sendForPriceDecrease,
            boolean sendForPriceIncrease,
            boolean sendForDescriptionChange,
            boolean sendForScoreChange,
            User user
    ) {
        validateUser(user);

        this.sendForPriceDecrease = sendForPriceDecrease;
        this.sendForPriceIncrease = sendForPriceIncrease;
        this.sendForDescriptionChange = sendForDescriptionChange;
        this.sendForScoreChange = sendForScoreChange;
        this.user = user;
    }

    void validateUser(User user) {
        if (user == null)
            throw new IllegalArgumentException("User can't be null");
    }

    public void setUser(User user) {
        validateUser(user);
        this.user = user;
    }
}
