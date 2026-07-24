package com.yuriolivs.redlinecore.infrastructure.service;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.service.IScoreCalculator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Component
public class ScoreCalculator implements IScoreCalculator {
    @Override
    public ScoreRecord calculate(Advertisement ad, double fipeValue, LocalDate referenceDate) {
        validateAdvertisement(ad);
        validateFipeValue(fipeValue);
        
        double fipeFactor = calculateFipeFactor(ad.getPrice(), fipeValue);
        double timeFactor = calculateTimeFactor(ad.getCreatedAt().toLocalDate(), referenceDate);
        double priceChangeFactor = calculatePriceChangeFactor(ad.getPriceHistory().size());

        double score = (fipeFactor * .40) + (priceChangeFactor * .35) + (timeFactor * .25);

        return new ScoreRecord((int) Math.round(score), LocalDate.now());
    }

    private double calculateFipeFactor(double adPrice, double fipePrice) {
        double distance = (adPrice - fipePrice) / fipePrice;
        return 1000 * Math.exp(-Math.pow(distance - .05, 2) / (2 * Math.pow(.15, 2)));
    }

    private double calculateTimeFactor(LocalDate followedDate, LocalDate referenceDate) {
        long daysOnAir = ChronoUnit.DAYS.between(followedDate, referenceDate);
        double normalized = Math.min(daysOnAir, 180) / 180.0;
        return 1000 * Math.pow(normalized, 2);
    }

    private double calculatePriceChangeFactor(int priceChangeCount) {
        return 1000 * Math.exp(-Math.pow((priceChangeCount - 1) - 1, 2) / (2 * Math.pow(1.5, 2)));
    }
    
    private void validateAdvertisement(Advertisement ad) {
        if (ad == null)
            throw new IllegalArgumentException("Advertisement can't be null");
    }
    
    private void validateFipeValue(double fipeValue) {
        if (fipeValue <= 0)
            throw new IllegalArgumentException("FIPE value must be greater than 0");
    }
}
