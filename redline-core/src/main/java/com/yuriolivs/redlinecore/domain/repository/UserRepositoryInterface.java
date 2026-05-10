package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.user.User;

import java.util.Optional;

public interface UserRepositoryInterface {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findByNameAndLastName(String name, String lastName);
    void remove(User user);
}
