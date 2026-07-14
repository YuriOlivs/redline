package com.yuriolivs.redlinecore.infrastructure;

import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.PriceRecord;
import com.yuriolivs.redlinecore.domain.advertisement.ScoreRecord;
import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ScoreCalculatorTest {
    private ScoreCalculator scoreCalculator;
    private Advertisement advertisement;

    @BeforeEach
    void setUp() {
        scoreCalculator = new ScoreCalculator();

        advertisement = new Advertisement(
                "https://webmotors.com.br/anuncio",
                "webmotors",
                "São Paulo, SP",
                true,
                90000,
                new Vehicle("Nissan", "Sentra", 2015),
                List.of(new PriceRecord(60000.0, LocalDate.parse("2026-05-01"))),
                LocalDate.now(),
                List.of(new ScoreRecord(500, LocalDate.parse("2026-05-01"))),
                LocalDateTime.now()
        );
    }

    @Test
    void shouldCalculateScoreSuccessfullyWhenTimeFactorIsZero() {
        ScoreRecord score = scoreCalculator.calculate(advertisement, 57000.00, LocalDate.now());

        assertNotNull(score);
        assertNotEquals(0, score.getValue());
        assertEquals(680, score.getValue());
    }

    @Test
    void shouldCalculateScoreSuccessfullyWhenTimeFactorIsNotZero() {
        Advertisement advertisement2 = new Advertisement(
                "https://webmotors.com.br/anuncio",
                "webmotors",
                "São Paulo, SP",
                true,
                90000,
                new Vehicle("Nissan", "Sentra", 2015),
                List.of(new PriceRecord(60000.0, LocalDate.parse("2026-05-01"))),
                LocalDate.now(),
                List.of(new ScoreRecord(500, LocalDate.parse("2026-05-01"))),
                LocalDateTime.parse("2026-05-15T20:15:30")
        );

        ScoreRecord score = scoreCalculator.calculate(advertisement2, 57000.00, LocalDate.now());

        assertNotNull(score);
        assertNotEquals(0, score.getValue());
        assertEquals(688, score.getValue());
    }

    @Test
    void shouldThrowExceptionWhenAdvertisementIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreCalculator.calculate(null, 57000.00, LocalDate.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenFipeValueIsEqualToZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreCalculator.calculate(advertisement, 0.0, LocalDate.now());
        });
    }

    @Test
    void shouldThrowExceptionWhenFipeValueIsLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            scoreCalculator.calculate(advertisement, -57000.0, LocalDate.now());
        });
    }
}
