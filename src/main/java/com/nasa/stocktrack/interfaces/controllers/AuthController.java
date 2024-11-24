package com.nasa.stocktrack.interfaces.controllers;

import com.nasa.stocktrack.application.usecases.user.SignInUseCase;
import com.nasa.stocktrack.interfaces.dtos.auth.SignInRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.auth.TokenResponseDTO;
import com.nasa.stocktrack.interfaces.openapi.controllers.AuthControllerOpenAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController implements AuthControllerOpenAPI {

    private final SignInUseCase signInUseCase;

    @Override
    @PostMapping(value = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenResponseDTO> signIn(@Validated @RequestBody SignInRequestDTO signInRequestDTO) {

        String accessToken = signInUseCase.execute(signInRequestDTO.toDomain());

        return ResponseEntity.ok(TokenResponseDTO.toResponse(accessToken));
    }
}
