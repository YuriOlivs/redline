package com.yuriolivs.redlinecore.application.advertisement;

import com.yuriolivs.redlinecore.application.advertisement.usecase.SaveAdUseCase;
import com.yuriolivs.redlinecore.domain.advertisement.Advertisement;
import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.IAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.ISavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class SaveAdUseCaseTest {
    private ISavedAdvertisementRepository savedAdRepository;
    private IAdvertisementRepository advertisementRepository;
    private IUserRepository userRepository;
    private SaveAdUseCase useCase;

    private UUID userId;
    private String url;
    private User user;
    private Advertisement advertisement;

    @BeforeEach
    void setUp() {
        savedAdRepository = Mockito.mock(ISavedAdvertisementRepository.class);
        advertisementRepository = Mockito.mock(IAdvertisementRepository.class);
        userRepository = Mockito.mock(IUserRepository.class);
        useCase = new SaveAdUseCase(savedAdRepository, advertisementRepository, userRepository);

        userId = UUID.randomUUID();
        url = "https://webmotors.com.br/anuncio/123";
        user = Mockito.mock(User.class);
        advertisement = Mockito.mock(Advertisement.class);

        Mockito.when(user.getId()).thenReturn(userId);
        Mockito.when(advertisement.getUrl()).thenReturn(url);
        Mockito.when(advertisement.isActive()).thenReturn(true);
    }

    @Test
    void shouldSaveAdvertisementSuccessfully() {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(advertisementRepository.findByUrl(url)).thenReturn(Optional.of(advertisement));
        Mockito.when(savedAdRepository.save(Mockito.any(SavedAdvertisement.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        SavedAdvertisement result = useCase.execute(url, userId);

        assertNotNull(result);
        Mockito.verify(savedAdRepository).save(Mockito.any(SavedAdvertisement.class));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenUserDoesNotExist() {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> useCase.execute(url, userId));

        Mockito.verify(savedAdRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenAdvertisementDoesNotExist() {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(advertisementRepository.findByUrl(url)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> useCase.execute(url, userId));

        Mockito.verify(savedAdRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void shouldThrowExceptionWhenAdvertisementIsNotActive() {
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        Mockito.when(advertisementRepository.findByUrl(url)).thenReturn(Optional.of(advertisement));
        Mockito.when(advertisement.isActive()).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> useCase.execute(url, userId));

        Mockito.verify(savedAdRepository, Mockito.never()).save(Mockito.any());
    }
}