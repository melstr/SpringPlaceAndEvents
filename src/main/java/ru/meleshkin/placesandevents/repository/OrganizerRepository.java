package ru.meleshkin.placesandevents.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.meleshkin.placesandevents.domain.entity.Organizer;

import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
public interface OrganizerRepository extends JpaRepository<Organizer, UUID> {

    Page<Organizer> findAllByOrganizationId(UUID organizationId, Pageable pageable);

    Organizer findByOrganizationIdAndUserId (UUID organizationId, UUID userId);
}
