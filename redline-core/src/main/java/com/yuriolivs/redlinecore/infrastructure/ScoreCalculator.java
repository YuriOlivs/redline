package com.yuriolivs.redlinecore.infrastructure;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.service.ScoreCalculatorInterface;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class ScoreCalculator implements ScoreCalculatorInterface {
    @Override
    public ScoreRecord calculate(Advertisement ad, double fipeValue) {
        validateAdvertisement(ad);
        validateFipeValue(fipeValue);
        
        double fipeFactor = calculateFipeFactor(ad.getPrice(), fipeValue);
        double timeFactor = calculateTimeFactor(ad.getCreatedAt().toLocalDate());
        double priceChangeFactor = calculatePriceChangeFactor(ad.getPriceHistory().size());

        double score = (fipeFactor * .40) + (priceChangeFactor * .35) + (timeFactor * .25);

        return new ScoreRecord((int) Math.round(score), LocalDate.now());
    }

    private double calculateFipeFactor(double adPrice, double fipePrice) {
        double distance = (adPrice - fipePrice) / fipePrice;
        return 1000 * Math.exp(-Math.pow(distance - .05, 2) / (2 * Math.pow(.15, 2)));
    }

    private double calculateTimeFactor(LocalDate followedDate) {
        long daysOnAir = ChronoUnit.DAYS.between(followedDate, LocalDate.now());;
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
