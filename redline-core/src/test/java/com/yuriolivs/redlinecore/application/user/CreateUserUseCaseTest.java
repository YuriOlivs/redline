package com.yuriolivs.redlinecore.application.user;

import com.yuriolivs.redlinecore.application.user.usecase.CreateUserUseCase;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.security.IPasswordEncrypter;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserUseCaseTest {
    private IUserRepository userRepository;
    private IPasswordEncrypter encrypter;
    private CreateUserUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(IUserRepository.class);
        encrypter = Mockito.mock(IPasswordEncrypter.class);
        useCase = new CreateUserUseCase(userRepository, encrypter);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456",
                LocalDate.now().minusYears(20)
        );

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Mockito.when(encrypter.encrypt(Mockito.any(String.class)))
                .thenReturn(String.join("_", "hashed", user.getPassword()));

        User userCreated = useCase.execute(
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getDateOfBirth()
        );

        assertNotNull(userCreated);
        assertEquals("Yuri", userCreated.getName());
        assertEquals(
                String.join("_", "hashed", user.getPassword()),
                userCreated.getPassword()
        );

        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void assertThrowsExceptionWhenEmailAlreadyExists() {
        Mockito.when(userRepository.findByEmail("yuri@email.com")).thenReturn(Optional.of(Mockito.mock(User.class)));

        assertThrows(IllegalArgumentException.class, () ->
            useCase.execute(
                    "Yuri",
                    "Oliveira",
                    "yuri@email.com",
                    "Senha@123456",
                    LocalDate.now().minusYears(20)
            )
        );

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowsExceptionWhenPasswordIsTooShort() {
        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Sen",
                LocalDate.now().minusYears(20)
        );

        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDateOfBirth()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenPasswordIsTooBig() {
        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456ggsdfgsdfgsdfgdgzdfgdg",
                LocalDate.now().minusYears(20)
        );


        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDateOfBirth()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveLetters() {
        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "@12345678910",
                LocalDate.now().minusYears(20)
        );


        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDateOfBirth()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveNumbers() {
        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@Senha!",
                LocalDate.now().minusYears(20)
        );


        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDateOfBirth()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveSpecialChars() {
        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha1234567",
                LocalDate.now().minusYears(20)
        );


        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDateOfBirth()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveUppercaseLetters() {
        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "senha@123456",
                LocalDate.now().minusYears(20)
        );


        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDateOfBirth()
            );
        });
    }

    @Test
    void assertThrowsExceptionWhenPasswordDoesNotHaveLowercaseLetters() {
        User user = new User(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "SENHA@123456",
                LocalDate.now().minusYears(20)
        );


        assertThrows(IllegalArgumentException.class, () -> {
            useCase.execute(
                    user.getName(),
                    user.getLastName(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getDateOfBirth()
            );
        });
    }
}
