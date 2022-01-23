package ru.meleshkin.placesandevents.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.meleshkin.placesandevents.domain.enums.UserType;
import ru.meleshkin.placesandevents.validation.annotation.Email;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PUBLIC;

/**
 * @author Meleshkin Alexandr
 * @since 17.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PUBLIC)
@Schema(name = "UserCreate", description = "Info to create user record")
public class UserCreateDto {
    @Schema(description = "User's login")
    @NotNull
    private String login;

    @Schema(description = "User's password")
    @NotNull
    private String password;

    @Schema(description = "User's name")
    private String name;

    @NotNull
    @Schema(description = "User's type")
    private UserType type;

    @Min(value = 0)
    @Max(value = 120)
    @Schema(description = "User's age")
    private Integer age;

    @Email
    @Schema(description = "User's email")
    String email;
}
