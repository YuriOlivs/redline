package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;

import java.time.LocalDate;

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
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
