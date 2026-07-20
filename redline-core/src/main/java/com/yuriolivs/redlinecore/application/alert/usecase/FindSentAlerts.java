package com.yuriolivs.redlinecore.application.alert.usecase;

import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.alert.AlertSearchCriteria;
import com.yuriolivs.redlinecore.domain.repository.IAlertRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindSentAlerts {
    private final IAlertRepository alertRepository;

    public List<Alert> execute(AlertSearchCriteria criteria) {
        return alertRepository.findByCriteria(criteria);
    }
}
