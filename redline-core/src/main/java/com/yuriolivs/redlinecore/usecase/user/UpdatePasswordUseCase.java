package com.yuriolivs.redlinecore.usecase.user;

import com.yuriolivs.redlinecore.domain.exceptions.NotFoundException;
import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.security.PasswordEncrypter;
import com.yuriolivs.redlinecore.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public class UpdatePasswordUseCase {
    private final UserRepositoryInterface userRepository;
    private final PasswordEncrypter encrypter;

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$";

    public UpdatePasswordUseCase(UserRepositoryInterface userRepository, PasswordEncrypter encrypter) {
        this.userRepository = userRepository;
        this.encrypter = encrypter;
    }

    public boolean execute(
            UUID id,
            String oldPassword,
            String newPassword
    ) {
        validatePassword(newPassword);

        Optional<User> userFound = userRepository.findById(id);
        if(userFound.isEmpty())
            throw new NotFoundException("User");

        User user = userFound.get();

        if (!encrypter.matches(oldPassword, user.getPassword()))
            throw new IllegalArgumentException("Old password does not match");

        user.setPassword(encrypter.encrypt(newPassword));
        User savedUser = userRepository.save(user);

        return savedUser != null;
    }

    private void validatePassword(String password) {
        if (!password.trim().matches(PASSWORD_REGEX))
            throw new IllegalArgumentException("");
    }
}
