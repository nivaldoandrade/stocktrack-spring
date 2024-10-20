package com.nasa.stocktrack.application.usecases.user;

import com.nasa.stocktrack.application.gateways.UserGateway;
import com.nasa.stocktrack.domain.dtos.PaginatedList;
import com.nasa.stocktrack.domain.entities.User;
import com.nasa.stocktrack.domain.enums.OrderByEnum;

public class ListUserUseCase {

    private final UserGateway userGateway;

    public ListUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public PaginatedList<User> execute(Integer page, Integer size, OrderByEnum orderBy, String search) {
        return userGateway.list(page, size, orderBy, search);
    }
}
