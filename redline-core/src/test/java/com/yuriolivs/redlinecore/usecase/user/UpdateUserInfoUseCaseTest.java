package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;
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
        String newName = "Nino";
        String newLastName = "Silva";

        UUID userId = UUID.randomUUID();

        User user = new User(
                userId,
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456",
                LocalDate.now().minusYears(20)
        );

        Mockito.when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));

        Mockito.when(userRepository.save(Mockito.any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        User updatedUser = useCase.execute(
                userId,
                newName,
                newLastName
        );

        assertNotNull(updatedUser);
        assertEquals(userId, updatedUser.getId());
        assertEquals(newName, updatedUser.getName());
        assertEquals(newLastName, updatedUser.getLastName());

        Mockito.verify(userRepository).save(Mockito.any(User.class));
    }

    @Test
    void assertThrowsExceptionWhenUserIsNotFound() {
        UUID wrongUserId = UUID.randomUUID();
        String newName = "Nino";

        User user = new User(
                UUID.randomUUID(),
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456",
                LocalDate.now().minusYears(20)
        );

        Mockito.when(userRepository.findById(wrongUserId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () ->{
            User updatedUser = useCase.execute(
                    wrongUserId,
                    newName,
                    null
            );
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void assertThrowExceptionWhenNameAndLastNameAreBothEmpty() {
        UUID userId = UUID.randomUUID();
        String newName = "Nino";

        User user = new User(
                userId,
                "Yuri",
                "Oliveira",
                "yuri@email.com",
                "Senha@123456",
                LocalDate.now().minusYears(20)
        );

        Mockito.when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () ->{
            User updatedUser = useCase.execute(
                    userId,
                    newName,
                    null
            );
        });

        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any());
    }
}
