package ru.meleshkin.placesandevents.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.meleshkin.placesandevents.domain.dto.OrganizationDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizationInfoDto;
import ru.meleshkin.placesandevents.domain.entity.Organization;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper
public interface OrganizationMapper {
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    Organization fromDto(OrganizationDto organizationCreateDto);

    OrganizationDto toDto(Organization organization);

    OrganizationInfoDto toInfoDto(Organization organization);

    List<OrganizationInfoDto> toListInfoDto(List<Organization> organizations);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    Organization merge(@MappingTarget Organization target, Organization source);
}
