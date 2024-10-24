package com.nasa.stocktrack.infra.gateways.repository.user;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.Role;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.infra.gateways.repository.role.RoleMapper;
import com.nasa.stocktrack.infra.persistence.entities.RoleEntity;
import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    private final RoleMapper roleMapper;

    public UserMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UserEntity toEntity(User user) {
        RoleEntity roleEntity = roleMapper.toEntity(user.getRole());

        return new UserEntity(
                user.getId(),
                user.getFull_name(),
                user.getUsername(),
                user.getPassword(),
                roleEntity
        );
    }

    public User toDomain(UserEntity userEntity) {
        Role role = roleMapper.toDomain(userEntity.getRole());

        return new User(
                userEntity.getId(),
                userEntity.getFull_name(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                role
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
