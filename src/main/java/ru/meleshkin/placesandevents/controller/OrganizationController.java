package ru.meleshkin.placesandevents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.*;
import ru.meleshkin.placesandevents.mapper.OrganizationMapper;
import ru.meleshkin.placesandevents.mapper.OrganizerMapper;
import ru.meleshkin.placesandevents.service.OrganizationService;
import ru.meleshkin.placesandevents.service.OrganizerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;

/**
 * @author Meleshkin Alexandr
 * @since 21.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path ="organizations")
@Tag(name = "Organizations", description = "Controller for operations with organizations. Also assigning user permission to control it.")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;

    private final OrganizerService organizerService;
    private final OrganizerMapper organizerMapper;

    // TODO: 26.01.2022 When security is implemented, should be automatically assign creator to createdBy

    @Operation(description = "Create organization")
    @ApiResponse(responseCode = "200", description = "Organization created")
    @PostMapping
    public  ResponseEntity<OrganizationCreateDto> create(@RequestBody OrganizationCreateDto organizationCreateDto){

        return Optional.ofNullable(organizationCreateDto)
                .map(organizationMapper::fromCreateDto)
                .map(organizationService::create)
                .map(organizationMapper::toCreateDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @Operation(description = "Find organization by id")
    @ApiResponse(responseCode = "200", description = "Organization found")
    @ApiResponse(responseCode = "404", description = "Organization not found")
    @GetMapping("/{organizationId}")
    public ResponseEntity<OrganizationDto> get(@PathVariable(name = "organizationId") UUID organizationId){

        return Optional.ofNullable(organizationId)
                .map(organizationService::get)
                .map(organizationMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @Operation(description = "Find all organizations")
    @ApiResponse(responseCode = "200", description = "organizations found")
    @GetMapping()
    public ResponseEntity<List<OrganizationDto>> getAll(){

        return Optional.of(organizationService.getAllOrganizations())
                .map(organizationMapper::toListDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @Operation(description = "Update organization info")
    @ApiResponse(responseCode = "200", description = "organization updated")
    @PatchMapping("/{organizationId}")
    public ResponseEntity<OrganizationCreateDto> update(@PathVariable(name = "organizationId") UUID id, @RequestBody OrganizationCreateDto organizationCreateDto){

        return Optional.ofNullable(organizationCreateDto)
                .map(organizationMapper::fromCreateDto)
                .map(organization -> organizationService.update(id, organization))
                .map(organizationMapper::toCreateDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @Operation(description = "Delete organization")
    @ApiResponse(responseCode = "204", description = "Organization removed")
    @DeleteMapping
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@RequestBody UUID id){

        organizationService.delete(id);
    }

    @Operation(description = "Find all organizers for a given organizationId")
    @ApiResponse(responseCode = "200", description = "organizers found")
    @GetMapping("/{organizationId}/organizers/")
    public ResponseEntity<List<OrganizerDto>> getAllOrganizers(@PathVariable("organizationId") UUID organizationId){

        return Optional
                .ofNullable(organizerService.getAllByOrganizationId(organizationId))
                .map(organizerMapper::toDtoList)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }

    @Operation(description = "Find out if user is a organizer")
    @ApiResponse(responseCode = "200", description = "Organizer found")
    @ApiResponse(responseCode = "404", description = "Organizer not found")
    @GetMapping("/{organizationId}/organizers/{userId}")
    public ResponseEntity<OrganizerDto> getOrganizerByUserId(@PathVariable("organizationId") UUID organizationId,
                                                             @PathVariable("userId") UUID userId){

        return Optional
                .of(organizerService.getByOrganizationIdAndUserId(organizationId, userId))
                .map(organizerMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow();
    }


    @Operation(description = "Assign or update role of a organizer")
    @ApiResponse(responseCode = "200", description = "User updated")
    @PatchMapping("/{organizerId}")
    @PutMapping("/{organizationId}/organizers/")
    public ResponseEntity<OrganizerDto> assignOrUpdateOrganizer(@PathVariable("organizationId") UUID organizationId,
                                                                OrganizerAssignDto organizerAssignDto){

        return Optional.of(organizerService.assignOrUpdateOrganizer(organizationId, organizerAssignDto))
                .map(organizerMapper::toDto)
                .map(ResponseEntity::ok)
                .orElseThrow();

    }
}
