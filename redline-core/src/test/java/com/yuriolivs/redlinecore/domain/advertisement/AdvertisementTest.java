package com.yuriolivs.redlinecore.domain.advertisement;

import com.yuriolivs.redlinecore.domain.vehicle.Vehicle;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdvertisementTest {
    private static final Vehicle validVehicle = new Vehicle("Nissan", "Sentra", 2015);
    private static final List<PriceRecord> validPriceHistory = List.of(
            new PriceRecord(60000.0, LocalDate.parse("2026-05-01")),
            new PriceRecord(50000.0, LocalDate.parse("2026-05-05"))
    );
    private static final List<ScoreRecord> validScoreHistory = List.of(
            new ScoreRecord(500, LocalDate.parse("2026-05-01")),
            new ScoreRecord(600, LocalDate.parse("2026-05-05"))
    );

    public static final Advertisement VALID_ADVERTISEMENT = new Advertisement(
            "https://webmotors.com.br/anuncio",
            "webmotors",
            "São Paulo, SP",
            true,
            90000,
            validVehicle,
            validPriceHistory,
            LocalDate.now(),
            validScoreHistory,
            LocalDateTime.now()
    );

    @Test
    void assertCreateAdvertisementSuccessfullyWithValidData() {
        assertDoesNotThrow(() -> {
            new Advertisement(
                    VALID_ADVERTISEMENT.getUrl(),
                    VALID_ADVERTISEMENT.getWebsite(),
                    VALID_ADVERTISEMENT.getLocation(),
                    VALID_ADVERTISEMENT.isActive(),
                    VALID_ADVERTISEMENT.getMileage(),
                    VALID_ADVERTISEMENT.getVehicle(),
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenUrlIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    null,
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenWebsiteIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenLocationIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    null,
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenMileageIsNullOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    -90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenVehicleIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    null,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenPriceHistoryIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    List.of(),
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenPriceHistoryIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    null,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenScoreHistoryIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    List.of(),
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenScoreHistoryIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    null,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenUrlUpdateIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Advertisement ad = new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );

            ad.setUrl(null);
        });
    }

    @Test
    void assertThrowsExceptionWhenWebsiteUpdateIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Advertisement ad = new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );

            ad.setWebsite("");
        });
    }

    @Test
    void assertThrowsExceptionWhenLocationUpdateIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            Advertisement ad = new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );

            ad.setLocation(null);
        });
    }

    @Test
    void assertThrowsExceptionWhenMileageUpdateIsNullOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Advertisement ad = new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );

            ad.setMileage(-10);
        });
    }

    @Test
    void assertThrowsExceptionWhenPriceRecordPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            Advertisement ad = new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );

            ad.registerPriceChange(-1.0, LocalDate.now());
        });
    }

    @Test
    void assertThrowsExceptionWhenPriceRecordPriceIsNaN() {
        assertThrows(IllegalArgumentException.class, () -> {
            Advertisement ad = new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );

            ad.registerPriceChange(Double.parseDouble("one"), LocalDate.now());
        });
    }

    @Test
    void assertThrowsExceptionWhenPriceRecordPriceIsInfinite() {
        assertThrows(IllegalArgumentException.class, () -> {
            Advertisement ad = new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );

            ad.registerPriceChange(Double.POSITIVE_INFINITY, LocalDate.now());
        });
    }

    @Test
    void assertThrowsExceptionWhenPriceRecordDateIsFuture() {
        assertThrows(IllegalArgumentException.class, () -> {
            Advertisement ad = new Advertisement(
                    "https://webmotors.com.br/anuncio",
                    "webmotors",
                    "São Paulo, SP",
                    true,
                    90000,
                    validVehicle,
                    validPriceHistory,
                    LocalDate.now(),
                    validScoreHistory,
                    LocalDateTime.now()
            );

            ad.registerPriceChange(48000.0, LocalDate.parse("2030-01-01"));
        });
    }
}
