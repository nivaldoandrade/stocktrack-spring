package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.enums.OrderByEnum;
import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import com.nasa.stocktrack.infra.persistence.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserRepositoryGateways implements UserGateway {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    public UserRepositoryGateways(
            UserMapper userMapper,
            UserRepository userRepository
    ) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public PaginatedList<User> list(Integer page, Integer size, OrderByEnum orderBy, String search) {
        Sort.Direction direction = "desc".equalsIgnoreCase(orderBy.name())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, "full_name"));

        Page<UserEntity> usersEntity = userRepository.findAll(search, pageable);

        return userMapper.toListDomain(usersEntity);
    }

    @Override
    public User create(User user) {
        UserEntity userEntity = userMapper.toEntity(user);

        userEntity = userRepository.save(userEntity);

        return userMapper.toDomain(userEntity);
    }

    @Override
    public void update(User user) {
        UserEntity userEntity = userMapper.toEntity(user);

        userRepository.save(userEntity);
    }

    @Override
    public void delete(User user) {
        UserEntity userEntity = userMapper.toEntity(user);

        userRepository.delete(userEntity);
    }

    @Override
    public User findByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        return userEntity.map(userMapper::toDomain).orElse(null);
    }

    @Override
    public User findById(UUID id) {
        Optional<UserEntity> userEntity = userRepository.findById(id);

        return userEntity.map(userMapper::toDomain).orElse(null);
    }
}
