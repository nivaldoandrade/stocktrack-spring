package com.nasa.stocktrack.application.usecases.user;

import com.nasa.stocktrack.application.services.AuthService;
import com.nasa.stocktrack.domain.dtos.SignInDTO;

public class SignInUseCase {

    private final AuthService authService;

    public SignInUseCase(AuthService authService) {
        this.authService = authService;
    }

    public String execute(SignInDTO signInDTO) {
        return authService.createToken(signInDTO.username(), signInDTO.password());
    }
}
