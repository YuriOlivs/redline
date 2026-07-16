package com.yuriolivs.redlinecore.application.user.usecase;

import com.yuriolivs.redlinecore.domain.repository.UserRepositoryInterface;
import com.yuriolivs.redlinecore.domain.security.PasswordEncrypter;
import com.yuriolivs.redlinecore.domain.user.User;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class CreateUserUseCase {
    private final UserRepositoryInterface userRepository;
    private final PasswordEncrypter encrypter;
    public static final String PASSW0RD_REGEX = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){6,16}$";

    public User execute(
            String name,
            String lastName,
            String email,
            String password,
            LocalDate dateOfBirth
    ) {
        boolean alreadyExists = userRepository.findByEmail(email).isPresent();

        if (alreadyExists)
            throw new IllegalArgumentException("Email already exists. Please enter a new email");

        validatePassword(password);
        String hashPassword = encrypter.encrypt(password);

        User newUser = new User(name, lastName, email, hashPassword, dateOfBirth);
        return userRepository.save(newUser);
    }

    private void validatePassword(String password) {
        if (!password.trim().matches(PASSW0RD_REGEX))
            throw new IllegalArgumentException("Password must be valid");
    }
}
