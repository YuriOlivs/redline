package com.yuriolivs.redlinecore.domain.service;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;

public interface ScoreCalculatorInterface {
    ScoreRecord calculate(Advertisement advertisement, Double fipeValue);
}
