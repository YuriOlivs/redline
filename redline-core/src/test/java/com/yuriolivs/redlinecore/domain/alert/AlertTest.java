package com.yuriolivs.redlinecore.domain.alert;

import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementTest;
import com.yuriolivs.redlinecore.domain.user.UserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlertTest {

    @Test
    void assertCreateAlertSuccessfullyWithValidData() {
        assertDoesNotThrow(() -> {
            new Alert(
                    AlertType.PRICE_REDUCTION,
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenDateTimeIsFuture() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Alert(
                    AlertType.PRICE_REDUCTION,
                    UUID.randomUUID(),
                    UUID.randomUUID(),
                    LocalDateTime.parse("2030-06-15T10:30:00")
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenAdvertisementIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Alert(
                    AlertType.PRICE_REDUCTION,
                    null,
                    UUID.randomUUID(),
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenUserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Alert(
                    AlertType.PRICE_REDUCTION,
                    UUID.randomUUID(),
                    null,
                    LocalDateTime.now()
            );
        });
    }
}
