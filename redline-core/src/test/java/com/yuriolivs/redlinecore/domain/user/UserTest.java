package com.yuriolivs.redlinecore.domain.user;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    public static final User VALID_USER = new User(
            "Yuri",
            "Oliveira",
            "yuri@email.com",
            "Ab1!kL9",
            LocalDate.parse("2000-01-01"),
            null
    );

    @Test
    void shouldCreateUserSuccessfullyWithValidData() {
        assertDoesNotThrow(() ->
            new User(
                VALID_USER.getName(),
                VALID_USER.getLastName(),
                VALID_USER.getEmail(),
                VALID_USER.getPassword(),
                VALID_USER.getDateOfBirth(),
                null
            )
        );
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                "Y",
                "Oliveira",
                "yuri@email.com",
                "Ab1!kL9",
                LocalDate.parse("2000-01-01"),
                null
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenLastNameIsTooShort() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                "Yuri",
                "O",
                "yuri@email.com",
                "Ab1!kL9",
                LocalDate.parse("2000-01-01"),
                null
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenNameHasInvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                "Yur1",
                "Oliveira",
                "yuri@email.com",
                "Ab1!kL9",
                LocalDate.parse("2000-01-01"),
                null
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenLastNameHasInvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                "Yuri",
                "0l1v31r4",
                "yuri@email.com",
                "Ab1!kL9",
                LocalDate.parse("2000-01-01"),
                null
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenEmailIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                "Yuri",
                "Oliveira",
                "yuri@email",
                "Ab1!kL9",
                LocalDate.parse("2000-01-01"),
                null
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenPasswordIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "senha123",
                LocalDate.parse("2000-01-01"),
                null
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenIsUnderage() {
        assertThrows(IllegalArgumentException.class, () -> {
                LocalDate dateOfBirth = LocalDate.parse("2010-01-01");

                new User(
                    "Yuri",
                    "Oliveira",
                    "yuri@email.com",
                    "Ab1!kL9",
                    dateOfBirth,
                    null
                );

            }
        );
    }

    @Test
    void shouldThrowExceptionWhenNameUpdateIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(
                    "Yuri",
                    "Oliveira",
                    "yuri@email.com",
                    "Ab1!kL9",
                    LocalDate.parse("2000-01-01"),
                    null
            );

            user.setName("Y");
        });
    }

    @Test
    void shouldThrowExceptionWhenLastNameUpdateIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(
                    "Yuri",
                    "Oliveira",
                    "yuri@email.com",
                    "Ab1!kL9",
                    LocalDate.parse("2000-01-01"),
                    null
            );

            user.setLastName("O1");
        });
    }

    @Test
    void shouldThrowExceptionWhenDateOfBirthUpdateIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(
                    "Yuri",
                    "Oliveira",
                    "yuri@email.com",
                    "Ab1!kL9",
                    LocalDate.parse("2000-01-01"),
                    null
            );

            user.setDateOfBirth(LocalDate.parse("2010-01-01"));
        });
    }

    @Test
    void shouldThrowExceptionWhenEmailUpdateIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(
                    "Yuri",
                    "Oliveira",
                    "yuri@email.com",
                    "Ab1!kL9",
                    LocalDate.parse("2000-01-01"),
                    null
            );

            user.setEmail("yuri.com");
        });
    }

    @Test
    void shouldThrowExceptionWhenPasswordUpdateIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            User user = new User(
                    "Yuri",
                    "Oliveira",
                    "yuri@email.com",
                    "Ab1!kL9",
                    LocalDate.parse("2000-01-01"),
                    null
            );

            user.setPassword("senha123");
        });
    }
}
