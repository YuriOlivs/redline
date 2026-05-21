package com.yuriolivs.redlinecore.domain.alert;

import com.yuriolivs.redlinecore.domain.advertisement.AdvertisementTest;
import com.yuriolivs.redlinecore.domain.user.UserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlertTest {

    @Test
    void assertCreateAlertSuccessfullyWithValidData() {
        assertDoesNotThrow(() -> {
            new Alert(
                    AlertType.PRICE_REDUCTION,
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    UserTest.VALID_USER,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenDateTimeIsFuture() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Alert(
                    AlertType.PRICE_REDUCTION,
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    UserTest.VALID_USER,
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
                    UserTest.VALID_USER,
                    LocalDateTime.now()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenUserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Alert(
                    AlertType.PRICE_REDUCTION,
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    null,
                    LocalDateTime.now()
            );
        });
    }
}
