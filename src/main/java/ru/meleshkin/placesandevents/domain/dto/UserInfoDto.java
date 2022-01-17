package ru.meleshkin.placesandevents.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.meleshkin.placesandevents.domain.enums.UserType;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PUBLIC;

/**
 * @author Meleshkin Alexandr
 * @since 17.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PUBLIC)
public class UserInfoDto {
    private UUID id;
    private String login;
    private String password;
    private String name;
    private UserType type;
    private Integer age;
    String email;
}
