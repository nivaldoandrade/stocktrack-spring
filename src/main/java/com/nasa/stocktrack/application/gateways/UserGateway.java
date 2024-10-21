package com.nasa.stocktrack.application.gateways;

import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.enums.OrderByEnum;

import java.util.UUID;

public interface UserGateway {

    PaginatedList<User> list(Integer page, Integer size, OrderByEnum orderBy, String search);

    User create(User user);

    void update(User user);

    User findByUsername(String username);

    User findById(UUID id);
}
