package ru.meleshkin.placesandevents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.EventCreateDto;
import ru.meleshkin.placesandevents.domain.dto.EventDto;
import ru.meleshkin.placesandevents.mapper.EventMapper;
import ru.meleshkin.placesandevents.service.EventService;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author Meleshkin Alexandr
 * @since 03.03.2022
 */
@Tag(name = "EventController", description = "CRUD controller for Events.")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@RestController
@RequiredArgsConstructor
@RequestMapping()
public class EventController {

    private final EventService eventService;
    private final EventMapper eventMapper;

    @Operation(description = "Create event")
    @ApiResponse(responseCode = "200", description = "Event created")
    @PostMapping("/{organizationId}/events")
    public EventDto createEvent(@PathVariable("organizationId") UUID organizationId,
                                @RequestParam("organizerId") UUID organizerId,
                                EventCreateDto eventCreateDto){
        return Optional.ofNullable(eventCreateDto)
                .map(eventMapper::fromCreateDto)
                .map(event -> eventService.create(organizationId, organizerId, event))
                .map(eventMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Find all events for organization")
    @ApiResponse(responseCode = "200", description = "events found")
    @GetMapping("/{organizationId}/events")
    Page<EventDto> getPageOfEventsByOrganization(@PathVariable("organizationId") UUID organizationId,
                                              @RequestParam("page") Integer page,
                                              @RequestParam("size") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return Optional.of(organizationId)
                .map(it -> eventService.getByOrganizationId(organizationId, pageRequest))
                .map(it -> it.map(eventMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Delete event")
    @ApiResponse(responseCode = "204", description = "Organization event")
    @DeleteMapping("events")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@RequestBody UUID eventId){
        eventService.deleteByEventId(eventId);
    }

}
