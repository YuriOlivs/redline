package com.yuriolivs.redlinecore.application.user;

import com.yuriolivs.redlinecore.application.user.usecase.UpdatePasswordUseCase;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.security.IPasswordEncrypter;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UpdatePasswordUseCaseTest {
    private IUserRepository userRepository;
    private IPasswordEncrypter encrypter;
    private UpdatePasswordUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(IUserRepository.class);
        encrypter = Mockito.mock(IPasswordEncrypter.class);
        useCase = new UpdatePasswordUseCase(userRepository, encrypter);
    }

    @Test
    void assertUpdatesPasswordSuccessfully() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String newPassword = "123456@Senha";
        String hashedNewPassword = "hashed_123456@Senha";

        User user = new User(
                UUID.randomUUID(),
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.matches(oldPassword, hashedOldPassword))
                .thenReturn(true);

        Mockito.when(encrypter.encrypt(newPassword))
                .thenReturn(hashedNewPassword);

        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        boolean updatedSuccessfully = useCase.execute(user.getId(), oldPassword, newPassword);

        assertTrue(updatedSuccessfully);
        assertEquals(hashedNewPassword, user.getPassword());

        Mockito.verify(encrypter).matches(oldPassword, hashedOldPassword);
        Mockito.verify(encrypter).encrypt(newPassword);
        Mockito.verify(userRepository).save(user);
    }

    @Test
    void assertThrowsExceptionWhenOldPasswordsDoesNotMatch() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String wrongOldPassword = "Password@123456";
        String newPassword = "123456@Senha";

        User user = new User(
                UUID.randomUUID(),
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        Mockito.when(encrypter.matches(hashedOldPassword, wrongOldPassword))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            boolean updatedSuccessfully = useCase.execute(user.getId(), wrongOldPassword, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenNewPasswordIsTooShort() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String newPassword = "Sen";

        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.matches(oldPassword, hashedOldPassword))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            boolean updatedSuccessfully = useCase.execute(user.getId(), oldPassword, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenNewPasswordIsTooBig() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String newPassword = "Senha@123456ggsdfgsdfgsdfgdgzdfgdg";

        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.matches(oldPassword, hashedOldPassword))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            boolean updatedSuccessfully = useCase.execute(user.getId(), oldPassword, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenNewPasswordDoesNotHaveLetters() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String newPassword = "12345678910@";

        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.matches(oldPassword, hashedOldPassword))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            boolean updatedSuccessfully = useCase.execute(user.getId(), oldPassword, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenNewPasswordDoesNotHaveNumbers() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String newPassword = "Senha@Senha!";

        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.matches(oldPassword, hashedOldPassword))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            boolean updatedSuccessfully = useCase.execute(user.getId(), oldPassword, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenNewPasswordDoesNotHaveSpecialChars() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String newPassword = "Senha1234567";

        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.matches(oldPassword, hashedOldPassword))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            boolean updatedSuccessfully = useCase.execute(user.getId(), oldPassword, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenNewPasswordDoesNotHaveUppercaseLetters() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String newPassword = "senha@123456";

        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.matches(oldPassword, hashedOldPassword))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            boolean updatedSuccessfully = useCase.execute(user.getId(), oldPassword, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenNewPasswordDoesNotHaveLowercaseLetters() {
        String oldPassword = "Senha@123456";
        String hashedOldPassword = "hashed_Senha@123456";
        String newPassword = "SENHA@123456";

        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                hashedOldPassword,
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.matches(oldPassword, hashedOldPassword))
                .thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            boolean updatedSuccessfully = useCase.execute(user.getId(), oldPassword, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }
}
