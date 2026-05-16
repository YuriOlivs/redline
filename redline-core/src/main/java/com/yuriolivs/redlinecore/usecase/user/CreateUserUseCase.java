package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;

import java.time.LocalDate;
import java.util.Optional;

public class CreateUserUseCase {
    private final UserRepositoryInterface userRepository;

    public CreateUserUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(
            String name,
            String lastName,
            String email,
            String password,
            LocalDate dateOfBirth
    ) {
        Optional<User> alreadyExists = userRepository.findByEmail(email);

        if (alreadyExists.isPresent())
            throw new IllegalArgumentException("Email already exists. Please enter a new email");

        User newUser = new User(name, lastName, email, password, dateOfBirth);
        return userRepository.save(newUser);
    }
}
