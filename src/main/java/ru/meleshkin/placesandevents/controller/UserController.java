package ru.meleshkin.placesandevents.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.UserDto;
import ru.meleshkin.placesandevents.domain.dto.UserInfoDto;
import ru.meleshkin.placesandevents.service.UserService;
import ru.meleshkin.placesandevents.mapper.UserMapper;

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
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping
    public UserDto create(@RequestBody UserDto userDto){
        return Optional.ofNullable(userDto)
                .map(userMapper::fromDto)
                .map(userService::create)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/{userId}")
    public UserDto get(@PathVariable UUID userId) {
        return Optional.ofNullable(userId)
                .map(userService::get)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @GetMapping
    public List<UserInfoDto> getAll(){
        return Optional.of(userService.getAll())
                .map(userMapper::toInfoDtoList)
                .orElseThrow();
    }

    @DeleteMapping
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@RequestBody UUID id){
        userService.delete(id);
    }

    @PatchMapping("/{userId}")
    public UserDto update(@PathVariable(name = "userId") UUID id, @RequestBody UserDto userDto){
        return Optional.ofNullable(userDto)
                .map(userMapper::fromDto)
                .map(user -> userService.update(id, user))
                .map(userMapper::toDto)
                .orElseThrow();
    }
}
