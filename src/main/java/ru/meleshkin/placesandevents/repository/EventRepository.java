package ru.meleshkin.placesandevents.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.meleshkin.placesandevents.domain.entity.Event;

import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 03.03.2022
 */
public interface EventRepository extends JpaRepository<Event, UUID> {
    Page<Event> findAllByOrganizationId(UUID organizationId, Pageable pageable);
}
