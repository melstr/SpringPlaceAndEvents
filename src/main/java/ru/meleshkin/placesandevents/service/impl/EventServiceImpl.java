package ru.meleshkin.placesandevents.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meleshkin.placesandevents.domain.entity.Event;
import ru.meleshkin.placesandevents.mapper.EventMapper;
import ru.meleshkin.placesandevents.repository.EventRepository;
import ru.meleshkin.placesandevents.service.EventService;
import ru.meleshkin.placesandevents.service.OrganizationService;
import ru.meleshkin.placesandevents.service.OrganizerService;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 03.03.2022
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventServiceImpl implements EventService {

    private final EventMapper eventMapper;
    private final EventRepository eventRepository;
    private final OrganizerService organizerService;
    private final OrganizationService organizationService;

    @Override
    @SneakyThrows
    @Transactional
    public Event create(UUID organizationId, UUID organizerId, Event event) {
        if(!event.getStartDateTime().isBefore(event.getEndDateTime())){
            throw new RuntimeException("Start time of the event should be before the end time");
        }

        if(!event.getStartDateTime().isAfter(LocalDateTime.now())){
            throw new RuntimeException("Start time of the event should be some time from now");
        }

        event.setOrganization(organizationService.get(organizationId));
        event.setOrganizer(organizerService.get(organizerId));
        return eventRepository.save(event);
    }

    @Override
    public Page<Event> getByOrganizationId(UUID organizationId, Pageable pageable) {
        return eventRepository.findAllByOrganizationId(organizationId, pageable);
    }

    public Event getById(UUID eventId){
        return eventRepository.findById(eventId).orElseThrow();
    }

    @Override
    public void deleteByEventId(UUID eventId) {
        eventRepository.delete(getById(eventId));
    }
}
