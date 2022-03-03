package ru.meleshkin.placesandevents.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

import static lombok.AccessLevel.PUBLIC;

/**
 * @author Meleshkin Alexandr
 * @since 03.03.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = PUBLIC)
public class EventDto {
    UUID organizerId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime startDateTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime endDateTime;
    String title;
    String description;
}
