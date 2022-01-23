package ru.meleshkin.placesandevents.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.OrganizerDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizerInfoDto;
import ru.meleshkin.placesandevents.domain.entity.Organizer;
import ru.meleshkin.placesandevents.mapper.OrganizerMapper;
import ru.meleshkin.placesandevents.service.OrganizerService;

import java.util.Optional;

/**
 * @author Meleshkin Alexandr
 * @since 22.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="organizers")
public class OrganizerController {

    private final OrganizerService organizerService;
    private final OrganizerMapper organizerMapper;

    @PostMapping
    public OrganizerDto create(@RequestBody OrganizerDto organizerDto){
        return Optional.ofNullable(organizerDto)
                .map(organizerMapper::fromDto)
                .map(organizerService::create)
                .map(organizerMapper::toDto)
                .orElseThrow();
    }
}
