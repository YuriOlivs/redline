package com.yuriolivs.redlinecore.application.advertisement.usecase;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.ISavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class RemoveSavedAdUseCase {
    private final ISavedAdvertisementRepository savedAdRepository;
    private final IUserRepository userRepository;

    public void execute(
            UUID savedAdId,
            UUID userId
    ) {
        Optional<SavedAdvertisement> savedAdFound = savedAdRepository.findById(savedAdId);
        Optional<User> userFound = userRepository.findById(userId);

        if (savedAdFound.isEmpty())
            throw new NotFoundException("Saved advertisement not found");

        if (userFound.isEmpty())
            throw new NotFoundException("User not found");

        savedAdRepository.remove(savedAdFound.get());
    }
}
