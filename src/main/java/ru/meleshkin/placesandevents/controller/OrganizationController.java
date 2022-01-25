package ru.meleshkin.placesandevents.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.OrganizationCreateDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizationDto;
import ru.meleshkin.placesandevents.mapper.OrganizationMapper;
import ru.meleshkin.placesandevents.service.OrganizationService;

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
public class OrganizationController {
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;

    @PostMapping
    public OrganizationCreateDto create(@RequestBody OrganizationCreateDto organizationCreateDto){

        return Optional.ofNullable(organizationCreateDto)
                .map(organizationMapper::fromCreateDto)
                .map(organizationService::create)
                .map(organizationMapper::toCreateDto)
                .orElseThrow();
    }

    @GetMapping("/{organizationId}")
    public OrganizationDto get(@PathVariable(name = "organizationId") UUID organizationId){

        return Optional.ofNullable(organizationId)
                .map(organizationService::get)
                .map(organizationMapper::toDto)
                .orElseThrow();
    }

    @GetMapping()
    public List<OrganizationDto> getAll(){

        return Optional.of(organizationService.getAll())
                .map(organizationMapper::toListDto)
                .orElseThrow();
    }

    @PatchMapping("/{organizationId}")
    public OrganizationCreateDto update(@PathVariable(name = "organizationId") UUID id, @RequestBody OrganizationCreateDto organizationCreateDto){

        return Optional.ofNullable(organizationCreateDto)
                .map(organizationMapper::fromCreateDto)
                .map(organization -> organizationService.update(id, organization))
                .map(organizationMapper::toCreateDto)
                .orElseThrow();
    }

    @DeleteMapping
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@RequestBody UUID id){

        organizationService.delete(id);
    }
}
