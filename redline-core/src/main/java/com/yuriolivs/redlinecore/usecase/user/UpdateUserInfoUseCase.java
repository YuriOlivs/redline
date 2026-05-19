package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;

import java.time.LocalDate;

public class UpdateUserInfoUseCase {
    private final UserRepositoryInterface userRepository;

    public UpdateUserInfoUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    User execute(
            String name,
            String lastName,
            String email,
            LocalDate dateOfBirth
    ) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
