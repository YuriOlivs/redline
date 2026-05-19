package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateUserInfoUseCaseTest {
    private UserRepositoryInterface userRepository;
    private UpdateUserInfoUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepositoryInterface.class);
        useCase = new UpdateUserInfoUseCase(userRepository);
    }

    @Test
    void assertUpdatesUserInfoSuccessfully() {
        String newEmail = "nino@email.com";
        String newName = "Nino";
        UUID userId = UUID.randomUUID();

        User user = new User(
                userId,
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456",
                LocalDate.now().minusYears(20)
        );


    }
}
