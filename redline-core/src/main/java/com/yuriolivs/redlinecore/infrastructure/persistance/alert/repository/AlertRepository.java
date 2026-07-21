package com.yuriolivs.redlinecore.infrastructure.persistance.alert.repository;

import com.yuriolivs.redlinecore.domain.alert.Alert;
import com.yuriolivs.redlinecore.domain.alert.AlertSearchCriteria;
import com.yuriolivs.redlinecore.domain.repository.IAlertRepository;
import com.yuriolivs.redlinecore.infrastructure.persistance.alert.entity.AlertEntity;
import com.yuriolivs.redlinecore.infrastructure.persistance.alert.mapper.AlertPersistanceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AlertRepository implements IAlertRepository {
    private final AlertJpaRepository jpaRepository;
    private final AlertPersistanceMapper mapper;

    @Override
    public Alert save(Alert alert) {
        AlertEntity alertSaved = jpaRepository.save(mapper.toEntity(alert));
        return mapper.toDomain(alertSaved);
    }

    @Override
    public List<Alert> findByCriteria(AlertSearchCriteria criteria) {
        List<AlertEntity> alertsFound = jpaRepository.findBySearchCriteria(
                criteria.type(),
                criteria.advertisementId(),
                criteria.userId(),
                criteria.from(),
                criteria.to()
        );

        return alertsFound.stream().map(mapper::toDomain).toList();
    }

    @Override
    public void remove(Alert alert) {
        jpaRepository.delete(mapper.toEntity(alert));
    }
}
