package com.yuriolivs.redlinecore.domain.security;

public interface PasswordEncrypter {
    String encrypt(String password);
    boolean matches(String password, String hashedPassword);
}
