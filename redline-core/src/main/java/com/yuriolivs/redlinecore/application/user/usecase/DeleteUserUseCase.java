package com.yuriolivs.redlinecore.application.user.usecase;

import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.ISavedAdvertisementRepository;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class DeleteUserUseCase {
    private final IUserRepository userRepository;
    private final ISavedAdvertisementRepository savedAdRepository;

    public void execute(UUID id) {
        Optional<User> userFound = userRepository.findById(id);
        if (userFound.isEmpty())
            throw new NotFoundException("User");

        User user = userFound.get();

        savedAdRepository.removeAllByUser(user);
        userRepository.remove(user);
    }
}
