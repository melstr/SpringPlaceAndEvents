package ru.meleshkin.placesandevents.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.meleshkin.placesandevents.domain.dto.OrganizationDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizationInfoDto;
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
    public OrganizationDto create(@RequestBody OrganizationDto organizationCreateDto){
        return Optional.ofNullable(organizationCreateDto)
                .map(organizationMapper::fromDto)
                .map(organizationService::create)
                .map(organizationMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/{organizationId}")
    public OrganizationInfoDto get(@PathVariable(name = "organizationId") UUID organizationId){
        return Optional.ofNullable(organizationId)
                .map(organizationService::get)
                .map(organizationMapper::toInfoDto)
                .orElseThrow();
    }

    @GetMapping()
    public List<OrganizationInfoDto> getAll(){
        return Optional.of(organizationService.getAll())
                .map(organizationMapper::toListInfoDto)
                .orElseThrow();
    }
    @PatchMapping("/{organizationId}")
    public OrganizationDto update(@PathVariable(name = "organizationId") UUID id, @RequestBody OrganizationDto organizationDto){
        return Optional.ofNullable(organizationDto)
                .map(organizationMapper::fromDto)
                .map(organization -> organizationService.update(id, organization))
                .map(organizationMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@RequestBody UUID id){
        organizationService.delete(id);
    }
}
