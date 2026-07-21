package com.yuriolivs.redlinecore.infrastructure.persistence.user.mapper;

import com.yuriolivs.redlinecore.domain.user.User;
import com.yuriolivs.redlinecore.infrastructure.persistence.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {
    public UserEntity toEntity(User user) {
        return UserEntity
                .builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .dateOfBirth(user.getDateOfBirth())
                .build();
    }

    public User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getDateOfBirth()
        );
    }
}
