package com.yuriolivs.redlinecore.infrastructure.persistance.user.repository;

import com.yuriolivs.redlinecore.domain.repository.IUserRepository;
import com.yuriolivs.redlinecore.domain.user.User;
import com.yuriolivs.redlinecore.infrastructure.persistance.user.entity.UserEntity;
import com.yuriolivs.redlinecore.infrastructure.persistance.user.mapper.UserPersistanceMapper;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class UserRepository implements IUserRepository {
    private final UserJpaRepository jpaRepository;
    private final UserPersistanceMapper mapper;

    @Override
    public User save(User user) {
        UserEntity entitySaved = jpaRepository.save(mapper.toEntity(user));
        return mapper.toDomain(entitySaved);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByNameAndLastName(String name, String lastName) {
        return jpaRepository.findByNameAndLast(name, lastName).map(mapper::toDomain);
    }

    @Override
    public void remove(User user) {
        jpaRepository.delete(mapper.toEntity(user));
    }
}
