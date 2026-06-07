package com.yuriolivs.redlinecore.domain.advertisement;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ScoreRecord {
    private Integer value;
    private LocalDate dateTime;

    public ScoreRecord(Integer value, LocalDate dateTime) {
        validateScoreValue(value);
        validateDateTime(dateTime);

        this.value = value;
        this.dateTime = dateTime;
    }

    private void validateScoreValue(Integer value) {
        if (value < 0)
            throw new IllegalArgumentException("The score value must be positive greater than 0");
    }

    private void validateDateTime(LocalDate dateTime) {
        if (dateTime.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date time must be past or present");
    }

    private void setValue(Integer value) {
        validateScoreValue(value);
        this.value = value;
    }

    private void setDateTime(LocalDate dateTime) {
        validateDateTime(dateTime);
        this.dateTime = dateTime;
    }
}
