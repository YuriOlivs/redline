package com.yuriolivs.redlinecore.domain.alert;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alert {
    private AlertType type;
    private Advertisement advertisement;
    private User user;

    public Alert(AlertType type, Advertisement advertisement, User user) {
        this.type = type;
        this.advertisement = advertisement;
        this.user = user;
    }
}
