package ru.meleshkin.placesandevents.service;

import ru.meleshkin.placesandevents.domain.entity.Organization;
import ru.meleshkin.placesandevents.domain.entity.Organizer;

import java.util.List;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 21.01.2022
 */
public interface OrganizationService {

    public Organization create(Organization organization);

    public Organization update(UUID id, Organization organization);

    public Organization get(UUID id);

    public void delete(UUID id);

    public List<Organization> getAllOrganizations();

}
