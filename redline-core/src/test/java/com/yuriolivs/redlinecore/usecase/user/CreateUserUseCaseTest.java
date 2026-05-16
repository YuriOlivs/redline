package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserUseCaseTest {
    private UserRepositoryInterface userRepository;
    private CreateUserUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepositoryInterface.class);
        useCase = new CreateUserUseCase(userRepository);
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

        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        User userCreated = useCase.execute(
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456",
                LocalDate.now().minusYears(20)
        );

        assertNotNull(userCreated);
        assertEquals("Yuri", userCreated.getName());

        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
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
}
