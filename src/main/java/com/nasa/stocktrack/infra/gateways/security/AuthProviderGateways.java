package com.nasa.stocktrack.infra.gateways.security;

import com.nasa.stocktrack.application.gateways.AuthProviderGateway;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.infra.gateways.repository.user.UserMapper;
import com.nasa.stocktrack.infra.persistence.entities.UserEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthProviderGateways implements AuthProviderGateway {

    private final AuthenticationProvider authenticationProvider;

    private final UserMapper userMapper;

    public AuthProviderGateways(
            AuthenticationProvider authenticationProvider,
            UserMapper userMapper
    ) {
        this.authenticationProvider = authenticationProvider;
        this.userMapper = userMapper;
    }

    @Override
    public User authenticate(String username, String password) {
        Authentication authentication = authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        return userMapper.toDomain(userEntity);
    }
}
