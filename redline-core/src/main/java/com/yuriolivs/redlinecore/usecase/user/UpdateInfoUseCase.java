package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.user.User;

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
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
