package com.yuriolivs.redlinecore.domain.alert;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.user.User;

import java.time.LocalDateTime;

public class AlertSearchCriteria {
    private AlertType type;
    private Advertisement advertisement;
    private User user;
    private LocalDateTime from;
}
