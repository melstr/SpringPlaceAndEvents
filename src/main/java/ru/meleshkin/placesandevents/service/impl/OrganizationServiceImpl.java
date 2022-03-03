package ru.meleshkin.placesandevents.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meleshkin.placesandevents.domain.entity.Organization;
import ru.meleshkin.placesandevents.domain.entity.Organizer;
import ru.meleshkin.placesandevents.mapper.OrganizationMapper;
import ru.meleshkin.placesandevents.repository.OrganizationRepository;
import ru.meleshkin.placesandevents.service.OrganizationService;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 21.01.2022
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Transactional
    @Override
    public Organization create(Organization organization) {
        return organizationRepository.save(organization);
    }

    @Transactional
    @Override
    public Organization update(UUID id, Organization organization) {
        Organization current = get(id);
        Organization toUpdate = organizationMapper.merge(current, organization);
        return organizationRepository.save(toUpdate);
    }

    @Override
    public Organization get(UUID id) {
        return organizationRepository.getById(id);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        organizationRepository.delete(get(id));
    }

    @Transactional
    @Override
    public Page<Organization> getAllOrganizations(Pageable pageable) {
        return organizationRepository.findAll(pageable);
    }




}
