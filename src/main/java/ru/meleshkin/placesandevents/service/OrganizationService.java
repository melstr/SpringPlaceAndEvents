package ru.meleshkin.placesandevents.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meleshkin.placesandevents.domain.entity.Organization;
import ru.meleshkin.placesandevents.domain.entity.Organizer;

import java.util.List;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 21.01.2022
 */
public interface OrganizationService {

    Organization create(Organization organization);

    Organization update(UUID id, Organization organization);

    Organization get(UUID id);

    void delete(UUID id);

    Page<Organization> getAllOrganizations(Pageable pageable);

}
