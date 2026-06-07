package com.yuriolivs.redlinecore.usecase.advertisement;

import com.yuriolivs.redlinecore.domain.advertisement.SavedAdvertisement;
import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepositoryInterface;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public class RemoveSavedAdUseCase {
    private final SavedAdvertisementRepositoryInterface savedAdRepository;
    private final UserRepositoryInterface userRepository;

    public RemoveSavedAdUseCase(SavedAdvertisementRepositoryInterface savedAdRepository, UserRepositoryInterface userRepository) {
        this.savedAdRepository = savedAdRepository;
        this.userRepository = userRepository;
    }

    public boolean execute(
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
        return savedAdRepository.findById(savedAdId).isEmpty();
    }
}
