package com.yuriolivs.redlinecore.domain.alert;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record AlertSearchCriteria(
        AlertType type,
        UUID advertisementId,
        UUID userId,
        LocalDateTime from,
        LocalDateTime to
) {}
