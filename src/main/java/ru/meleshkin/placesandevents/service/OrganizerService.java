package ru.meleshkin.placesandevents.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    Page<Organizer> getAllByOrganizationId(UUID organizationId, Pageable pageable);

    Organizer assignOrUpdateOrganizer(UUID organizationId, OrganizerAssignDto organizerAssignDto);

}
