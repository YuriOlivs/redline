package com.yuriolivs.redlinecore.infrastructure.security;

import com.yuriolivs.redlinecore.domain.security.IPasswordEncrypter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Argon2IPasswordEncrypter implements IPasswordEncrypter {

    private final Argon2PasswordEncoder encoder = Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();

    @Override
    public String encrypt(String password) {
        return encoder.encode(password);
    }

    @Override
    public boolean matches(String password, String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }
}
