package ru.meleshkin.placesandevents.mapper;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.meleshkin.placesandevents.domain.dto.OrganizerCreateDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizerDto;
import ru.meleshkin.placesandevents.domain.entity.Organizer;
import ru.meleshkin.placesandevents.repository.OrganizationRepository;
import ru.meleshkin.placesandevents.repository.UserRepository;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

/**
 * @author Meleshkin Alexandr
 * @since 22.01.2022
 */
@Mapper
public abstract class OrganizerMapper {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected OrganizationRepository organizationRepository;

    @Autowired
    protected UserRepository userMapper;

    @Autowired
    protected OrganizationRepository organizationMapper;

    @Mappings({
            @Mapping(target = "user", expression = "java(userRepository.findById(organizerCreateDto.getUserId()).orElseThrow())"),
            @Mapping(target = "organization", expression = "java(organizationRepository.findById(organizerCreateDto.getOrganizationId()).orElseThrow())")
    })
    public abstract Organizer fromCreateDto(OrganizerCreateDto organizerCreateDto);

    @Mappings({
            @Mapping(target = "userId", expression = "java(organizer.getUser().getId())"),
            @Mapping(target = "organizationId", expression = "java(organizer.getOrganization().getId())")
    })
    public abstract OrganizerCreateDto toCreateDto(Organizer organizer);

    @Mappings({
            @Mapping(target = "userId", expression = "java(organizer.getUser().getId())"),
            @Mapping(target = "organizationId", expression = "java(organizer.getOrganization().getId())")
    })
    public abstract OrganizerDto toDto(Organizer organizer);

    public abstract List<OrganizerDto> toDtoList(List<Organizer> organizers);

    @BeanMapping(nullValuePropertyMappingStrategy = IGNORE)
    public abstract Organizer merge(@MappingTarget Organizer target, Organizer source);
}
