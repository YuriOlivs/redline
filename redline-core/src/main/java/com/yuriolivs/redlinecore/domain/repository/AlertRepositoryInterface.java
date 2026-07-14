package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.alert.AlertSearchCriteria;
import com.yuriolivs.redlinecore.domain.alert.AlertType;
import com.yuriolivs.redlinecore.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AlertRepositoryInterface {
    Alert save(Alert alert);
    List<Alert> findByCriteria(AlertSearchCriteria criteria);
    void remove(Alert alert);
}
