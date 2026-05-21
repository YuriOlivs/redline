package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.SavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public class DeleteUserUseCase {
    private final UserRepositoryInterface userRepository;
    private final SavedAdvertisementRepository savedAdRepository;

    public DeleteUserUseCase(
            UserRepositoryInterface userRepository,
            SavedAdvertisementRepository savedAdRepository
    ) {
        this.userRepository = userRepository;
        this.savedAdRepository = savedAdRepository;
    }

    void execute(UUID id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isEmpty())
            throw new NotFoundException("User");

        User user = userFound.get();

        savedAdRepository.removeAllByUser(user);
        userRepository.remove(user);
    }
}
