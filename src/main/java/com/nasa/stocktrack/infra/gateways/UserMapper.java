package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public PaginatedList<User> toListDomain(Page<UserEntity> userEntityPage) {
        List<User> users = userEntityPage.map(this::toDomain).toList();

        return new PaginatedList<User>(
                users,
                userEntityPage.getTotalElements(),
                userEntityPage.getTotalPages()
        );
    }
}
