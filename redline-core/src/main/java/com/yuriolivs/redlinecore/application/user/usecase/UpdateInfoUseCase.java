package com.yuriolivs.redlinecore.application.user.usecase;

import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UpdateInfoUseCase {
    private final IUserRepository userRepository;

    public User execute(
            UUID id,
            String name,
            String lastName
    ) {
        if(name == null || name.isEmpty() && lastName == null || lastName.isEmpty())
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
