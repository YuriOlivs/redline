package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;

public class ForgotUserPasswordUseCase {
    private final UserRepositoryInterface userRepository;

    public ForgotUserPasswordUseCase(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    boolean execute() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
