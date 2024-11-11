package com.nasa.stocktrack.interfaces.dtos.auth;

import com.nasa.stocktrack.domain.dtos.SignInDTO;

public record SignInRequestDTO(
        String username,

        String password
) {

    public SignInDTO toDomain() {
        return new SignInDTO(username, password);
    }
}
