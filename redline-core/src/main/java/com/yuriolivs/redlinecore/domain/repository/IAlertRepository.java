package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.alert.AlertSearchCriteria;

import java.util.List;

public interface IAlertRepository {
    Alert save(Alert alert);
    List<Alert> findByCriteria(AlertSearchCriteria criteria);
    void remove(Alert alert);
}
