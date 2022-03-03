package ru.meleshkin.placesandevents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.OrganizerAssignDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizerDto;
import ru.meleshkin.placesandevents.mapper.OrganizerMapper;
import ru.meleshkin.placesandevents.service.OrganizerService;

import java.util.Optional;
import java.util.UUID;


/**
 * Controller for assigning users to make them organizers of an organization
 * Basically CRUD for organizers
 *
 * @author Meleshkin Alexandr
 * @since 22.01.2022
 */


@Tag(name = "Organizer", description = "CRUD controller for Organizers.")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="/{organizationId}/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;
    private final OrganizerMapper organizerMapper;

    @Operation(description = "Find all organizers for a given organizationId")
    @ApiResponse(responseCode = "200", description = "organizers found")
    @GetMapping()
    public Page<OrganizerDto> getAll(@PathVariable("organizationId") UUID organizationId,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("size") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return Optional.of(organizationId)
                .map(it -> organizerService.getAllByOrganizationId(it, pageRequest))
                .map(it -> it.map(organizerMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Find out if user is a organizer")
    @ApiResponse(responseCode = "200", description = "Organizer found")
    @ApiResponse(responseCode = "404", description = "Organizer not found")
    @GetMapping("/{userId}")
    public OrganizerDto getOrganizerByUserId(@PathVariable("organizationId") UUID organizationId,
                                             @PathVariable("userId") UUID userId){
        return Optional
                .of(organizerService.getByOrganizationIdAndUserId(organizationId, userId))
                .map(organizerMapper::toDto)
                .orElseThrow();
    }


    @Operation(description = "Assign or update role of a organizer")
    @ApiResponse(responseCode = "200", description = "User updated")
    @PutMapping
    public OrganizerDto assignOrUpdateOrganizer(@PathVariable("organizationId") UUID organizationId,
                                                OrganizerAssignDto organizerAssignDto){
        return Optional.of(organizerService.assignOrUpdateOrganizer(organizationId, organizerAssignDto))
                .map(organizerMapper::toDto)
                .orElseThrow();
    }
}
