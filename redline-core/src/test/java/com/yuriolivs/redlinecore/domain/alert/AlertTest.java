package com.yuriolivs.redlinecore.domain.alert;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlertTest {
    @Test
    void assertCreateAlertSuccessfullyWithValidData() {}

    @Test
    void assertThrowsExceptionWhenDateTimeIsFuture() {}

    @Test
    void assertThrowsExceptionWhenAdvertisementIsNull() {}

    @Test
    void assertThrowsExceptionWhenUserIsNull() {}

    @Test
    void assertThrowsExceptionWhenTypeIsInvalid() {}
}
