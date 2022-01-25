package ru.meleshkin.placesandevents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.OrganizerCreateDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizerDto;
import ru.meleshkin.placesandevents.mapper.OrganizerMapper;
import ru.meleshkin.placesandevents.service.OrganizerService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * Controller for assigning users to make them organizers of a organization
 * Basically CRUD for organizers
 *
 * @author Meleshkin Alexandr
 * @since 22.01.2022
 */


@Tag(name = "Organizer", description = "Controller for assigning users to make them organizers of a organization")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="organizers")
public class OrganizerController {

    private final OrganizerService organizerService;
    private final OrganizerMapper organizerMapper;

    /**
     * Assigns a user to an organization, and gives role to one
     * Made for users who will have some control over organization profile
     *
     * @param organizerCreateDto data for creating user record
     * @return Data of a user in JSON format
     */
    // TODO: 25.01.2022 Rename for a assign user for an organization
    @Operation(description = "Create organizer")
    @ApiResponse(responseCode = "200", description = "Organizer created")
    @PostMapping
    public OrganizerCreateDto create(@RequestBody OrganizerCreateDto organizerCreateDto){

        return Optional.ofNullable(organizerCreateDto)
                .map(organizerMapper::fromCreateDto)
                .map(organizerService::create)
                .map(organizerMapper::toCreateDto)
                .orElseThrow();
    }

    /**
     * Returns data of an organizer in JSON format
     *
     * @param organizerId id of a organizer you are trying to get information from
     * @return Data of an organizer in JSON format
     */
    @Operation(description = "Find organizer by id")
    @ApiResponse(responseCode = "200", description = "Organizer found")
    @ApiResponse(responseCode = "404", description = "Organizer not found")
    @GetMapping("/{organizerId}")
    public ResponseEntity<OrganizerCreateDto> get(@Valid @PathVariable UUID organizerId) {

        return Optional.ofNullable(organizerId)
                .map(organizerService::get)
                .map(organizerMapper::toCreateDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    /**
     * Returns data of all organizers in JSON format
     *
     * @return Data of all organizer in JSON format
     */
    @Operation(description = "Find all organizers")
    @ApiResponse(responseCode = "200", description = "organizers found")
    @GetMapping
    public ResponseEntity<List<OrganizerDto>> getAll(){

        return Optional.of(organizerService.getAll())
                .map(organizerMapper::toDtoList)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    /**
     * Deletes organizer of an organizer by id
     *
     * @param organizerId - id of a organizer you're deleting
     */
    @Operation(description = "Delete organizer")
    @ApiResponse(responseCode = "204", description = "Organizer removed")
    @DeleteMapping
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@RequestBody UUID organizerId){

        organizerService.delete(organizerId);
    }

    /**
     * Updates organizer data. Nulls are ignored.
     *
     * @param organizerId id of an organizer you are trying to get information from
     * @return Data of a organizer in JSON format
     */
    // TODO: 25.01.2022 Make it make sense. Only role should be updated. Need of an updateDTO.
    // TODO: Maybe make it consume organizationId and userId not organizerId. Therefore possibly there should be createOrUpdate() method.
    // TODO: Think of some way that there can be only be a one record of a user for one organization
    @Operation(description = "Update organizer")
    @ApiResponse(responseCode = "200", description = "Organizer updated")
    @PatchMapping("/{organizerId}")
    public ResponseEntity<OrganizerDto> update(@PathVariable(name = "organizerId") UUID organizerId, @RequestBody OrganizerCreateDto organizerCreateDto){
        return Optional.ofNullable(organizerCreateDto)
                .map(organizerMapper::fromCreateDto)
                .map(organizer -> organizerService.update(organizerId, organizer))
                .map(organizerMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }
}
