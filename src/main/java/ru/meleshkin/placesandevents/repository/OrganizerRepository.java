package ru.meleshkin.placesandevents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.meleshkin.placesandevents.domain.entity.Organizer;

import java.util.List;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
public interface OrganizerRepository extends JpaRepository<Organizer, UUID> {

    List<Organizer> findAllByOrganizationId(UUID organizationId);

    Organizer findByOrganizationIdAndUserId (UUID organizationId, UUID userId);
}
