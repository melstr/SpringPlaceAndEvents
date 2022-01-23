package ru.meleshkin.placesandevents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.UserCreateDto;
import ru.meleshkin.placesandevents.domain.dto.UserDto;
import ru.meleshkin.placesandevents.service.UserService;
import ru.meleshkin.placesandevents.mapper.UserMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * CRUD for users
 *
 * @author Meleshkin Alexandr
 * @since 17.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="users")
@Tag(name = "User", description = "Controller for operations with users' records")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "User not found")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Creates user record
     *
     * @param userCreateDto data for creating user record
     * @return Data of a user in JSON format
     */
    @Operation(description = "Create user")
    @ApiResponse(responseCode = "200", description = "User created")
    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateDto userCreateDto){
        return Optional.ofNullable(userCreateDto)
                .map(userMapper::fromDto)
                .map(userService::create)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    /**
     * Returns data of a user in JSON format
     *
     * @param userId id of a user you are trying to get information from
     * @return Data of a user in JSON format
     */
    @Operation(description = "Find user by id")
    @ApiResponse(responseCode = "200", description = "User found")
    @ApiResponse(responseCode = "404", description = "User not found")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> get(@Valid @PathVariable UUID userId) {
        return Optional.ofNullable(userId)
                .map(userService::get)
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    /**
     * Returns data of all users in JSON format
     *
     * @return Data of all users in JSON format
     */
    @Operation(description = "Find all users")
    @ApiResponse(responseCode = "200", description = "Users found")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll(){
        return Optional.of(userService.getAll())
                .map(userMapper::toDtoList)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    /**
     * Deletes record of a user by id
     *
     * @param id - id of a user you're deleting
     */
    @Operation(description = "Delete user")
    @ApiResponse(responseCode = "204", description = "User removed")
    @DeleteMapping
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@RequestBody UUID id){

        userService.delete(id);
    }

    /**
     * Updates user data. Nulls are ignored.
     *
     * @param userId id of a user you are trying to get information from
     * @return Data of a user in JSON format
     */
    @Operation(description = "Update user")
    @ApiResponse(responseCode = "200", description = "User updated")
    @PatchMapping("/{userId}")
    public ResponseEntity<UserDto> update(@PathVariable(name = "userId") UUID userId, @RequestBody UserCreateDto userCreateDto){
        return Optional.ofNullable(userCreateDto)
                .map(userMapper::fromDto)
                .map(user -> userService.update(userId, user))
                .map(userMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }
}
