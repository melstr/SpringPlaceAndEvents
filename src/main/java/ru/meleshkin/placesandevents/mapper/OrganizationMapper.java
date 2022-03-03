package ru.meleshkin.placesandevents.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.meleshkin.placesandevents.domain.dto.OrganizationCreateDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizationDto;
import ru.meleshkin.placesandevents.domain.entity.Organization;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface OrganizationMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    @Mapping(target = "organizers", ignore = true)
    @Mapping(target = "events", ignore = true)
    Organization fromCreateDto(OrganizationCreateDto organizationCreateDto);

    OrganizationCreateDto toCreateDto(Organization organization);

    OrganizationDto toDto(Organization organization);

    List<OrganizationDto> toListDto(List<Organization> organizations);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(target = "organizers", ignore = true)
    Organization merge(@MappingTarget Organization target, Organization source);
}
