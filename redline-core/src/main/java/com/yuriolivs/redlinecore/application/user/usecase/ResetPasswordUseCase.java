package com.yuriolivs.redlinecore.application.user.usecase;

import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.security.IPasswordEncrypter;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class ResetPasswordUseCase {
    private final IUserRepository userRepository;
    private final IPasswordEncrypter encrypter;

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$";

    public boolean execute(UUID userId, String newPassword) {
        validatePassword(newPassword);

        Optional<User> userFound = userRepository.findById(userId);
        if (userFound.isEmpty())
            throw new NotFoundException("User");

        User user = userFound.get();
        user.setPassword(encrypter.encrypt(newPassword));

        User savedUser = userRepository.save(user);
        return savedUser != null;
    }

    private void validatePassword(String password) {
        if (!password.trim().matches(PASSWORD_REGEX))
            throw new IllegalArgumentException("");
    }
}
