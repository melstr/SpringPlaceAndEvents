package ru.meleshkin.placesandevents.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import ru.meleshkin.placesandevents.domain.enums.OrganizerRole;

import java.util.UUID;

import static lombok.AccessLevel.PUBLIC;

/**
 * @author Meleshkin Alexandr
 * @since 22.01.2022
 */

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PUBLIC)
public class OrganizerDto {
    UUID userId;
    OrganizerRole role;
    UUID organizationId;
}
