package ru.meleshkin.placesandevents.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import ru.meleshkin.placesandevents.domain.dto.OrganizerDto;
import ru.meleshkin.placesandevents.domain.dto.OrganizerInfoDto;
import ru.meleshkin.placesandevents.domain.entity.Organizer;
import ru.meleshkin.placesandevents.repository.OrganizationRepository;
import ru.meleshkin.placesandevents.repository.UserRepository;

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
            @Mapping(target = "user", expression = "java(userRepository.findById(organizerDto.getUserId()).orElseThrow())"),
            @Mapping(target = "organization", expression = "java(organizationRepository.findById(organizerDto.getOrganizationId()).orElseThrow())")
    })
    public abstract Organizer fromDto(OrganizerDto organizerDto);

    @Mappings({
            @Mapping(target = "userId", expression = "java(organizer.getUser().getId())"),
            @Mapping(target = "organizationId", expression = "java(organizer.getOrganization().getId())")
    })
    public abstract OrganizerDto toDto(Organizer organizer);

    @Mappings({
            @Mapping(target = "userId", expression = "java(organizer.getUser().getId())"),
            @Mapping(target = "organizationId", expression = "java(organizer.getOrganization().getId())")
    })
    public abstract OrganizerInfoDto toInfoDto(Organizer organizer);
}
