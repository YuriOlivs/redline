package com.yuriolivs.redlinecore.domain.vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VehicleTest {
    @Test
    void assertCreateVehicleSuccessfullyWithValidData() {
        assertDoesNotThrow(() -> {
            new Vehicle(
                    "Nissan",
                    "Sentra",
                    2015
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenBrandIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Vehicle(
                    "",
                    "Sentra",
                    2015
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenModelIsEmptyOrNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Vehicle(
                    "Nissan",
                    "",
                    2015
            );
        });
    }

    @Test
    void asserThrowsExceptionWhenYearIsFuture() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Vehicle(
                    "Nissan",
                    "Sentra",
                    2035
            );
        });
    }
}
