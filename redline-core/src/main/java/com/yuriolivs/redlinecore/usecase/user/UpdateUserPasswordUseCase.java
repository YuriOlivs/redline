package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.security.PasswordEncrypter;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.UUID;

public class UpdateUserPasswordUseCase {
    private final UserRepositoryInterface userRepository;
    private final PasswordEncrypter encrypter;

    public UpdateUserPasswordUseCase(UserRepositoryInterface userRepository, PasswordEncrypter encrypter) {
        this.userRepository = userRepository;
        this.encrypter = encrypter;
    }

    public boolean execute(
            UUID id,
            String oldPassword,
            String newPassword
    ) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
