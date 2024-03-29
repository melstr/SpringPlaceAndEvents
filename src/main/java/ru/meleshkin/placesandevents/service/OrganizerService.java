package ru.meleshkin.placesandevents.service;

import ru.meleshkin.placesandevents.domain.dto.OrganizerAssignDto;
import ru.meleshkin.placesandevents.domain.entity.Organizer;

import java.util.List;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
public interface OrganizerService {

    Organizer create(Organizer organizer);

    void delete(UUID id);

    List<Organizer> getAll();

    Organizer get(UUID id);

    Organizer update(UUID id, Organizer organizer);

    Organizer getByOrganizationIdAndUserId(UUID organizationId, UUID userId);

    List<Organizer> getAllByOrganizationId(UUID organizationId);

    Organizer assignOrUpdateOrganizer(UUID organizationId, OrganizerAssignDto organizerAssignDto);

}
