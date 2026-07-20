package com.yuriolivs.redlinecore.application.advertisement;

import com.yuriolivs.redlinecore.application.advertisement.usecase.RemoveSavedAdUseCase;
import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.ISavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveSavedAdUseCaseTest {
    private ISavedAdvertisementRepository savedAdRepository;
    private IUserRepository userRepository;
    private RemoveSavedAdUseCase useCase;

    private UUID userId;
    private UUID savedAdId;
    private SavedAdvertisement savedAdvertisement;

    @BeforeEach
    void setUp() {
        savedAdRepository = Mockito.mock(ISavedAdvertisementRepository.class);
        userRepository = Mockito.mock(IUserRepository.class);
        IUserRepository userRepository = Mockito.mock(IUserRepository.class);
        useCase = new RemoveSavedAdUseCase(savedAdRepository, userRepository);

        userId = UUID.randomUUID();
        savedAdId = UUID.randomUUID();
        savedAdvertisement = Mockito.mock(SavedAdvertisement.class);

        User user = Mockito.mock(User.class);
        Mockito.when(user.getId()).thenReturn(userId);
        Mockito.when(savedAdvertisement.getUser()).thenReturn(user);

        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
    }

    @Test
    void shouldRemoveSavedAdvertisementSuccessfully() {
        Mockito.when(savedAdRepository.findById(savedAdId))
                .thenReturn(Optional.of(savedAdvertisement));

        useCase.execute(savedAdId, userId);

        Mockito.verify(savedAdRepository).remove(savedAdvertisement);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenSavedAdvertisementDoesNotExist() {
        Mockito.when(savedAdRepository.findById(savedAdId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> useCase.execute(savedAdId, userId));

        Mockito.verify(savedAdRepository, Mockito.never()).remove(Mockito.any());
    }
}