package com.yuriolivs.redlinecore.application.user;

import com.yuriolivs.redlinecore.application.user.usecase.DeleteUserUseCase;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteUserUseCaseTest {
    private UserRepositoryInterface userRepository;
    private SavedAdvertisementRepositoryInterface savedAdRepository;
    private DeleteUserUseCase useCase;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepositoryInterface.class);
        savedAdRepository = Mockito.mock(SavedAdvertisementRepositoryInterface.class);
        useCase = new DeleteUserUseCase(userRepository, savedAdRepository);
    }

    @Test
    void assertDeletesUserSuccessfully() {
        User user = new User(
                UUID.randomUUID(),
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456",
                LocalDate.now().minusYears(20)
        );

        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.of(user));

        useCase.execute(user.getId());

        Mockito.when(userRepository.findById(user.getId()))
                .thenReturn(Optional.empty());


        Mockito.verify(savedAdRepository).removeAllByUser(user);
        Mockito.verify(userRepository).remove(user);
    }

    @Test
    void assertThrowsExceptionWhenUserIsNotFound() {
        UUID wrongUserId = UUID.randomUUID();

        Mockito.when(userRepository.findById(wrongUserId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            useCase.execute(wrongUserId);
        });

        Mockito.verify(userRepository, Mockito.never()).remove(Mockito.any());
    }
}
