package ru.meleshkin.placesandevents.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import static lombok.AccessLevel.PUBLIC;

/**
 * @author Meleshkin Alexandr
 * @since 21.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PUBLIC)
public class OrganizationCreateDto {

    private String name;
    private String description;
}
