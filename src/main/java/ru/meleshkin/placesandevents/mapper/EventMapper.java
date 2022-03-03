package ru.meleshkin.placesandevents.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.meleshkin.placesandevents.domain.dto.EventCreateDto;
import ru.meleshkin.placesandevents.domain.dto.EventDto;
import ru.meleshkin.placesandevents.domain.entity.Event;
import ru.meleshkin.placesandevents.repository.EventRepository;

/**
 * @author Meleshkin Alexandr
 * @since 03.03.2022
 */
@Mapper
public abstract class EventMapper {

    @Autowired
    EventRepository eventRepository;

    @Mapping(target = "organizer", ignore = true)
    @Mapping(target = "organization", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "modifiedAt", ignore = true)
    public abstract Event fromCreateDto(EventCreateDto eventCreateDto);

    @Mapping(target = "organizerId", expression = "java(event.getOrganizer().getId())")
    public abstract EventDto toDto(Event event);
}
