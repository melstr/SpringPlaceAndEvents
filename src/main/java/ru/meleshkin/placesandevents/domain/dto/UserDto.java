package ru.meleshkin.placesandevents.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.meleshkin.placesandevents.domain.enums.UserType;

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
public class UserDto {
    private String login;
    private String password;
    private String name;
    private UserType type;
    private Integer age;
    String email;
}
