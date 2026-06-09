package com.yuriolivs.redlinecore.infrastructure;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.service.ScoreCalculatorInterface;

import java.time.LocalDate;
import java.time.Period;

public class ScoreCalculator implements ScoreCalculatorInterface {
    @Override
    public ScoreRecord calculate(Advertisement ad, Double fipeValue) {
        Double fipeFactor = calculateFipeFactor(ad.getPrice(), fipeValue);
        Double timeFactor = calculateTimeFactor(ad.getCreatedAt().toLocalDate());
        Double priceChangeFactor = calculatePriceChangeFactor(ad.getPriceHistory().size());

        Double score = (fipeFactor * .40) + (priceChangeFactor * .35) + (timeFactor * .25);
        return new ScoreRecord((int) Math.round(score), LocalDate.now());
    }

    private Double calculateFipeFactor(Double adPrice, Double fipePrice) {
        double distance = (adPrice - fipePrice) / fipePrice;
        return 1000 * Math.exp(-Math.pow(distance - .05, 2) / (2 * Math.pow(.15, 2)));
    }

    private Double calculateTimeFactor(LocalDate followedDate) {
        Integer daysOnAir = Period.between(followedDate, LocalDate.now()).getDays();
        double normalized = Math.min(daysOnAir, 180) / 180.0;
        return 1000 * Math.pow(normalized, 2);
    }

    private Double calculatePriceChangeFactor(Integer priceChangeCount) {
        return 1000 * Math.exp(-Math.pow(priceChangeCount - 1, 2) / (2 * Math.pow(1.5, 2)));
    }
}
