package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.security.PasswordEncrypter;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ResetPasswordUseCaseTest {
    private UserRepositoryInterface userRepository;
    private PasswordEncrypter encrypter;
    private ResetPasswordUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepositoryInterface.class);
        encrypter = Mockito.mock(PasswordEncrypter.class);
        useCase = new ResetPasswordUseCase(userRepository, encrypter);
    }

    @Test
    void assertResetsPasswordSuccessfully() {
        String newPassword = "123456@Senha";
        String hashedNewPassword = "hashed_123456@Senha";

        User user = new User(
                UUID.randomUUID(),
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "hashed_Senha@123456",
                LocalDate.now().minusYears(20)
        );

        Mockito.when(encrypter.encrypt(newPassword))
                .thenReturn(hashedNewPassword);

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        User updatedUser = useCase.execute(user.getId(), newPassword);

        assertNotNull(updatedUser);
        assertEquals(newPassword, updatedUser.getPassword());
    }

    @Test
    void assertThrowsExceptionWhenUserIsNotFound() {
        UUID wrongId = UUID.randomUUID();
        String newPassword = "123456@Senha";

        Mockito.when(userRepository.findById(wrongId))
                .thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(wrongId, newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenPasswordIsTooShort() {
        String newPassword = "S@1";

        assertThrows(IllegalAccessError.class, () -> {
            useCase.execute(UUID.randomUUID(), newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenPasswordIsTooBig() {
        String newPassword = "Senha@123456akjdsflksdjf";

        assertThrows(IllegalAccessError.class, () -> {
            useCase.execute(UUID.randomUUID(), newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveLetters() {
        String newPassword = "12345678910";

        assertThrows(IllegalAccessError.class, () -> {
            useCase.execute(UUID.randomUUID(), newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveNumbers() {
        String newPassword = "Senha@Senha!";

        assertThrows(IllegalAccessError.class, () -> {
            useCase.execute(UUID.randomUUID(), newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveSpecialCharacters() {
        String newPassword = "Senha1234567";

        assertThrows(IllegalAccessError.class, () -> {
            useCase.execute(UUID.randomUUID(), newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveUppercaseLetters() {
        String newPassword = "senha@123456";

        assertThrows(IllegalAccessError.class, () -> {
            useCase.execute(UUID.randomUUID(), newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveLowercaseLetters() {
        String newPassword = "SENHA@123456";

        assertThrows(IllegalAccessError.class, () -> {
            useCase.execute(UUID.randomUUID(), newPassword);
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }
}
