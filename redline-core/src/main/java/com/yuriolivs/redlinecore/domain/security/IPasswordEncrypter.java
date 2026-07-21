package com.yuriolivs.redlinecore.domain.security;

public interface IPasswordEncrypter {
    String encrypt(String password);
    boolean matches(String password, String hashedPassword);
}
