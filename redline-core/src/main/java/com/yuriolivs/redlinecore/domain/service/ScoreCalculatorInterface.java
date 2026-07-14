package com.yuriolivs.redlinecore.domain.service;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;

import java.time.LocalDate;

public interface ScoreCalculatorInterface {
    ScoreRecord calculate(Advertisement advertisement, double fipeValue, LocalDate referenceDate);
}
