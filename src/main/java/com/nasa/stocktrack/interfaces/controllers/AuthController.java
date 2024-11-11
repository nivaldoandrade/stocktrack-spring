package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.services.AuthService;
import com.nasa.stocktrack.application.usecases.user.SignInUseCase;
import com.nasa.stocktrack.interfaces.dtos.auth.SignInRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.auth.TokenResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final SignInUseCase signInUseCase;

    @PostMapping("/signin")
    public ResponseEntity<TokenResponseDTO> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {

        String accessToken = signInUseCase.execute(signInRequestDTO.toDomain());

        return ResponseEntity.ok(TokenResponseDTO.toResponse(accessToken));
    }
}
