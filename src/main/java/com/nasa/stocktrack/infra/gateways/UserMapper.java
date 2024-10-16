package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getFull_name(),
                user.getUsername(),
                user.getPassword()
        );
    }

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFull_name(),
                userEntity.getUsername(),
                userEntity.getPassword()
        );
    }
}
