package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public class UpdateInfoUseCase {
    private final UserRepositoryInterface userRepository;

    public UpdateInfoUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    User execute(
            UUID id,
            String name,
            String lastName
    ) {
        if(name == null && lastName == null || name.isEmpty() && lastName.isEmpty())
            throw new IllegalArgumentException("One of the following fields must be filled: name, lastName");

        Optional<User> userFound = userRepository.findById(id);
        if(userFound.isEmpty())
            throw new NotFoundException("User");

        User user = userFound.get();
        user.setName(name);
        user.setLastName(lastName);

        return userRepository.save(user);
    }
}
