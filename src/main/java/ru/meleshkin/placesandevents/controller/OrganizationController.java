package ru.meleshkin.placesandevents.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.OrganizationCreateDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizationDto;
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


    @Operation(description = "Create organization")
    @ApiResponse(responseCode = "200", description = "Organization created")
    @PostMapping
    public  OrganizationCreateDto create(@RequestBody OrganizationCreateDto organizationCreateDto){
        return Optional.ofNullable(organizationCreateDto)
                .map(organizationMapper::fromCreateDto)
                .map(organizationService::create)
                .map(organizationMapper::toCreateDto)
                .orElseThrow();
    }

    @Operation(description = "Find organization by id")
    @ApiResponse(responseCode = "200", description = "Organization found")
    @ApiResponse(responseCode = "404", description = "Organization not found")
    @GetMapping("/{organizationId}")
    public OrganizationDto get(@PathVariable(name = "organizationId") UUID organizationId){
        return Optional.ofNullable(organizationId)
                .map(organizationService::get)
                .map(organizationMapper::toDto)
                .orElseThrow();
    }

    @Operation(description = "Find all organizations")
    @ApiResponse(responseCode = "200", description = "organizations found")
    @GetMapping()
    public Page<OrganizationDto> getAll(@RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return Optional.of(pageRequest)
                .map(organizationService::getAllOrganizations)
                .map(it -> it.map(organizationMapper::toDto))
                .orElseThrow();
    }

    @Operation(description = "Update organization info")
    @ApiResponse(responseCode = "200", description = "organization updated")
    @PatchMapping("/{organizationId}")
    public OrganizationCreateDto update(@PathVariable(name = "organizationId") UUID id, @RequestBody OrganizationCreateDto organizationCreateDto){
        return Optional.ofNullable(organizationCreateDto)
                .map(organizationMapper::fromCreateDto)
                .map(organization -> organizationService.update(id, organization))
                .map(organizationMapper::toCreateDto)
                .orElseThrow();
    }

    @Operation(description = "Delete organization")
    @ApiResponse(responseCode = "204", description = "Organization removed")
    @DeleteMapping
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@RequestBody UUID id){
        organizationService.delete(id);
    }


}
