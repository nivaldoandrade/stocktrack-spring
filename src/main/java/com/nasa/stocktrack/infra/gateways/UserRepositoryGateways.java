package com.nasa.stocktrack.infra.gateways;

import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import com.nasa.stocktrack.infra.persistence.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public User create(User user) {
        UserEntity userEntity = userMapper.toEntity(user);

        userEntity = userRepository.save(userEntity);

        return userMapper.toDomain(userEntity);
    }

    @Override
    public User findByUsername(String username) {
        Optional<UserEntity> userEntity = userRepository.findByUsername(username);

        return userEntity.map(userMapper::toDomain).orElse(null);
    }
}
