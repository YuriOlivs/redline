package com.yuriolivs.redlinecore.domain.advertisement;

import com.yuriolivs.redlinecore.domain.user.UserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SavedAdvertisementTest {
    @Test
    void assertCreateSavedAdvertisementWithValidData() {
        assertDoesNotThrow(() -> {

        });
    }

    @Test
    void assertThrowsExceptionWhenSavedDateIsFuture() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavedAdvertisement(
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    UserTest.VALID_USER,
                    LocalDate.parse("2030-01-01")
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenSavedDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavedAdvertisement(
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    UserTest.VALID_USER,
                    null
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenAdvertisementIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavedAdvertisement(
                    null,
                    UserTest.VALID_USER,
                    LocalDate.parse("2030-01-01")
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenUserIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavedAdvertisement(
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    null,
                    LocalDate.parse("2030-01-01")
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenSavedDateIsFutureDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            SavedAdvertisement savedAd = new SavedAdvertisement(
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    UserTest.VALID_USER,
                    LocalDate.parse("2030-01-01")
            );

            savedAd.setSavedDate(LocalDate.parse("2030-01-01"));
        });
    }

    @Test
    void assertThrowsExceptionWhenSavedDateIsNullDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            SavedAdvertisement savedAd = new SavedAdvertisement(
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    UserTest.VALID_USER,
                    LocalDate.parse("2030-01-01")
            );

            savedAd.setSavedDate(null);
        });
    }

    @Test
    void assertThrowsExceptionWhenAdvertisementIsNullDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            SavedAdvertisement savedAd = new SavedAdvertisement(
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    UserTest.VALID_USER,
                    LocalDate.parse("2030-01-01")
            );

            savedAd.setAdvertisement(null);
        });
    }

    @Test
    void assertThrowsExceptionWhenUserIsNullDuringSet() {
        assertThrows(IllegalArgumentException.class, () -> {
            SavedAdvertisement savedAd = new SavedAdvertisement(
                    AdvertisementTest.VALID_ADVERTISEMENT,
                    UserTest.VALID_USER,
                    LocalDate.parse("2030-01-01")
            );

            savedAd.setUser(null);
        });
    }
}
