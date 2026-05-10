package com.yuriolivs.redlinecore.domain.advertisement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdvertisementTest {
    @Test
    void assertCreateAdvertisementSuccessfullyWithValidData() {}

    @Test
    void assertThrowsExceptionWhenUrlIsEmptyOrNull() {}

    @Test
    void assertThrowsExceptionWhenWebsiteIsEmptyOrNull() {}

    @Test
    void assertThrowsExceptionWhenLocationIsEmptyOrNull() {}

    @Test
    void assertThrowsExceptionWhenMileageIsNullOrNegative() {}

    @Test
    void assertThrowsExceptionWhenPriceHistoryIsNullOrEmpty() {}

    @Test
    void assertThrowsExceptionWhenUrlUpdateIsEmptyOrNull() {}

    @Test
    void assertThrowsExceptionWhenWebsiteUpdateIsEmptyOrNull() {}

    @Test
    void assertThrowsExceptionWhenLocationUpdateIsEmptyOrNull() {}

    @Test
    void assertThrowsExceptionWhenMileageUpdateIsNullOrNegative() {}

    @Test
    void assertThrowsExceptionWhenPriceRecordPriceIsNegativeOrNaNOrInfinite() {}

    @Test
    void assertThrowsExceptionWhenPriceRecordDateIsFuture() {}
}
