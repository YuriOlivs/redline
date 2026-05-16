package com.yuriolivs.redlinecore.domain.advertisement;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PriceRecordTest {
    @Test
    public void assertCreatePriceRecordWithValidData() {
        assertDoesNotThrow(() -> {
            new PriceRecord(
                    10000.0,
                    LocalDate.parse("2026-01-01")
            );
        });
    }

    @Test
    public void assertThrowsExceptionWhenPriceIsNegative() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceRecord(
                    -10000.0,
                    LocalDate.parse("2026-01-01")
            );
        });
    }

    @Test
    public void assertThrowsExceptionWhenPriceIsNaN() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceRecord(
                    Double.parseDouble("s"),
                    LocalDate.parse("2026-01-01")
            );
        });
    }

    @Test
    public void assertThrowsExceptionWhenPriceIsInfinite() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceRecord(
                    Double.POSITIVE_INFINITY,
                    LocalDate.parse("2026-01-01")
            );
        });
    }

    @Test
    public void assertThrowsExceptionWhenPriceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceRecord(
                    null,
                    LocalDate.parse("2026-01-01")
            );
        });
    }

    @Test
    public void assertThrowsExceptionWhenDateIsFuture() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceRecord(
                    10000.0,
                    LocalDate.parse("2030-01-01")
            );
        });
    }

    @Test
    public void assertThrowsExceptionWhenDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PriceRecord(
                    10000.0,
                    null
            );
        });
    }

    @Test
    public void assertThrowsExceptionWhenPriceIsNegativeDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceRecord priceRecord = new PriceRecord(
                    10000.0,
                    LocalDate.parse("2026-01-01")
            );

            priceRecord.setPrice(-10000.0);
        });
    }

    @Test
    public void assertThrowsExceptionWhenPriceIsNaNDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceRecord priceRecord = new PriceRecord(
                    10000.0,
                    LocalDate.parse("2026-01-01")
            );

            priceRecord.setPrice(Double.parseDouble("s"));
        });
    }

    @Test
    public void assertThrowsExceptionWhenPriceIsInfiniteDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceRecord priceRecord = new PriceRecord(
                    10000.0,
                    LocalDate.parse("2026-01-01")
            );

            priceRecord.setPrice(Double.POSITIVE_INFINITY);
        });
    }

    @Test
    public void assertThrowsExceptionWhenPriceIsNullDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceRecord priceRecord = new PriceRecord(
                    10000.0,
                    LocalDate.parse("2026-01-01")
            );

            priceRecord.setPrice(null);
        });
    }

    @Test
    public void assertThrowsExceptionWhenDateIsFutureDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceRecord priceRecord = new PriceRecord(
                    10000.0,
                    LocalDate.parse("2026-01-01")
            );

            priceRecord.setDate(LocalDate.parse("2030-01-01"));
        });
    }

    @Test
    public void assertThrowsExceptionWhenDateIsNullDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            PriceRecord priceRecord = new PriceRecord(
                    10000.0,
                    LocalDate.parse("2026-01-01")
            );

            priceRecord.setDate(null);
        });
    }
}
