package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RemoveSavedAdUseCaseTest {
    private SavedAdvertisementRepositoryInterface savedAdRepository;
    private RemoveSavedAdUseCase useCase;

    private UUID userId;
    private UUID savedAdId;
    private SavedAdvertisement savedAdvertisement;

    @BeforeEach
    void setUp() {
        savedAdRepository = Mockito.mock(SavedAdvertisementRepositoryInterface.class);
        UserRepositoryInterface userRepository = Mockito.mock(UserRepositoryInterface.class);
        useCase = new RemoveSavedAdUseCase(savedAdRepository, userRepository);

        userId = UUID.randomUUID();
        savedAdId = UUID.randomUUID();
        savedAdvertisement = Mockito.mock(SavedAdvertisement.class);

        Mockito.when(savedAdvertisement.getUser().getId()).thenReturn(userId);
    }

    @Test
    void shouldRemoveSavedAdvertisementSuccessfully() {
        Mockito.when(savedAdRepository.findById(savedAdId))
                .thenReturn(Optional.of(savedAdvertisement));

        boolean result = useCase.execute(savedAdId, userId);

        assertTrue(result);
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