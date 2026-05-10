package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.alert.AlertType;
import com.yuriolivs.redlinecore.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlertRepositoryInterface {
    Alert save(Alert alert);
    Optional<List<Alert>> findAllByType(AlertType type);
    Optional<List<Alert>> findAllByAdvertisement(Advertisement ad);
    Optional<List<Alert>> findAllByUser(User user);
    Optional<List<Alert>> findAllByDateTime(LocalDateTime dateTime);
    void remove(Alert alert);
}
