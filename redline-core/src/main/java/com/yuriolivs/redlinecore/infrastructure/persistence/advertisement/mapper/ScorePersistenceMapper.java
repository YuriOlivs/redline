package com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.mapper;

import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.infrastructure.persistence.advertisement.entity.ScoreRecordEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ScorePersistenceMapper {
    public ScoreRecordEntity toEntity(ScoreRecord record) {
        return ScoreRecordEntity
                .builder()
                .id(UUID.randomUUID())
                .dateTime(record.getDateTime())
                .value(record.getValue())
                .build();
    }

    public ScoreRecord toDomain(ScoreRecordEntity entity) {
        return new ScoreRecord(
                entity.getValue(),
                entity.getDateTime()
        );
    }
}
