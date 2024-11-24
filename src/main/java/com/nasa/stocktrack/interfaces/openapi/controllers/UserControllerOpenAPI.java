package com.nasa.stocktrack.interfaces.openapi.controllers;

import com.nasa.stocktrack.infra.constraints.EnumOrderByPattern;
import com.nasa.stocktrack.infra.constraints.ValidUUID;
import com.nasa.stocktrack.infra.exceptions.RestErrorResponseWithErrors;
import com.nasa.stocktrack.interfaces.dtos.ListResponseDTO;
import com.nasa.stocktrack.interfaces.dtos.user.CreateUserRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.user.UpdateUserRequestDTO;
import com.nasa.stocktrack.interfaces.dtos.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "User", description = "User management API")
public interface UserControllerOpenAPI {

    @Operation(
            summary = "Retrieve all Users",
            description = "Get a Users array",
            parameters = {
                    @Parameter(
                            name = "page",
                            description = "Page number for pagination. Min value: 0",
                            example = "0"
                    ),
                    @Parameter(
                            name = "size",
                            description = "Number of items per page. Min value: 1 - Max value: 10",
                            example = "10"
                    ),
                    @Parameter(
                            name = "orderBy",
                            description = "Sort by ascendant or descendant",
                            schema = @Schema(
                                    types = "String",
                                    allowableValues = {"ASC", "DESC"},
                                    defaultValue = "ASC"
                            )
                    ),
            }
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content)
    })
    ResponseEntity<ListResponseDTO<UserDTO>> list(
            @RequestParam(name = "page", defaultValue = "0") @Min(0) Integer page,
            @RequestParam(name = "size", defaultValue = "10") @Min(1) @Max(10) Integer size,
            @RequestParam(name = "orderBy", defaultValue = "asc") @EnumOrderByPattern String orderBy,
            @RequestParam(required = false) String search
    );

    @Operation(
            summary = "Retrieve a User by accessToken",
            description = "Get a User by accessToken. The response is object of the UserDTO schema type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<UserDTO> me(Authentication authentication);

    @Operation(
            summary = "Retrieve a User by id",
            description = "Get a User by id. The response is object of the UserDTO schema type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200" ),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<UserDTO> show(@ValidUUID @PathVariable String id);

    @Operation(
            summary = "Create a User",
            description = "Create a new User by passing in a JSON representation of the CreateUserRequestDTO schema type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = RestErrorResponseWithErrors.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )}),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<UserDTO> create(@Validated @RequestBody CreateUserRequestDTO createUserRequestDTO);

    @Operation(
            summary = "Update a User by id",
            description = "Update a User by id. passing in a JSON representation of the UpdateUserRequestDTO schema type"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = {@Content(
                    schema = @Schema(implementation = RestErrorResponseWithErrors.class),
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )}),
            @ApiResponse(responseCode = "401", content = @Content),
            @ApiResponse(responseCode = "403", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content),
    })
    ResponseEntity<Void> update(
            @ValidUUID @PathVariable String id,
            @Validated @RequestBody UpdateUserRequestDTO updateUserRequestDTO
    );

    @Operation(
            summary = "Delete a User by id",
            description = "Delete a User by id"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204"),
            @ApiResponse(responseCode = "400", content = @Content),
            @ApiResponse(responseCode = "404", content = @Content)
    })
    ResponseEntity<Void> delete(
            @ValidUUID @PathVariable String id,
            Authentication authentication
    );
}
