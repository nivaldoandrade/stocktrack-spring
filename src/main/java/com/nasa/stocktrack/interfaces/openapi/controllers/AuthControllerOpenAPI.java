package com.nasa.stocktrack.interfaces.openapi.controllers;

import com.nasa.stocktrack.interfaces.dtos.auth.SignInRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.auth.TokenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Auth", description = "Auth management API")
public interface AuthControllerOpenAPI {

    @Operation(
            summary = "Auth in user",
            description = "Authenticate user with provided credentials and generate authentication token."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content)
    })
    ResponseEntity<TokenResponseDTO> signIn(@Validated @RequestBody SignInRequestDTO signInRequestDTO);
}
