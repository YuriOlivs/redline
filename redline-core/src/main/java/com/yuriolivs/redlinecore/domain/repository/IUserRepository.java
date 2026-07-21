package com.yuriolivs.redlinecore.domain.repository;

import com.yuriolivs.redlinecore.domain.user.User;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    Optional<User> findByEmail(String email);
    Optional<User> findByNameAndLastName(String name, String lastName);
    void remove(User user);
}
