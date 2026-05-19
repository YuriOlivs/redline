package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.security.PasswordEncrypter;

public class ResetPasswordUseCase {
    private final UserRepositoryInterface userRepository;
    private final PasswordEncrypter encrypter;

    public ResetPasswordUseCase(
            UserRepositoryInterface userRepository,
            PasswordEncrypter encrypter
    ) {
        this.userRepository = userRepository;
        this.encrypter = encrypter;
    }

    boolean execute(String newPassword) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
