package ru.meleshkin.placesandevents.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meleshkin.placesandevents.domain.entity.Event;

import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 03.03.2022
 */
public interface EventService {

    Event create(UUID organizationId, UUID organizerId, Event event);

    Page<Event> getByOrganizationId(UUID organizationId, Pageable pageable);

    void deleteByEventId(UUID eventId);

}
