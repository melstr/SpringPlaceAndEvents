package ru.meleshkin.placesandevents.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.meleshkin.placesandevents.domain.dto.OrganizerAssignDto;
import ru.meleshkin.placesandevents.domain.entity.Organizer;
import ru.meleshkin.placesandevents.mapper.OrganizerMapper;
import ru.meleshkin.placesandevents.repository.OrganizationRepository;
import ru.meleshkin.placesandevents.repository.OrganizerRepository;
import ru.meleshkin.placesandevents.service.OrganizerService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 22.01.2022
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;
    private final OrganizerMapper organizerMapper;
    private final OrganizationRepository organizationRepository;

    @Transactional
    @Override
    public Organizer create(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        organizerRepository.delete(get(id));
    }

    @Override
    public List<Organizer> getAll() {
        return organizerRepository.findAll();
    }

    @Override
    public Organizer get(UUID id) {
        return organizerRepository.findById(id).orElseThrow();
    }

    @Transactional
    @Override
    public Organizer update(UUID id, Organizer organizer) {
        final Organizer current = get(id);
        final Organizer toUpdate = organizerMapper.merge(current, organizer);
        return organizerRepository.save(toUpdate);
    }

    @Transactional
    @Override
    public Organizer getByOrganizationIdAndUserId(UUID organizationId, UUID userId) {
        return organizerRepository.findByOrganizationIdAndUserId(organizationId, userId);
    }

    @Transactional
    @Override
    public Page<Organizer> getAllByOrganizationId(UUID organizationId, Pageable pageable) {
        return organizerRepository.findAllByOrganizationId(organizationId, pageable);
    }

    /**
     * Updates or creates a role for user, to have permission to control organization
     *
     * @param organizationId     id of an organization
     * @param organizerAssignDto basically DTO with {@code userId} and {@code role}
     * @return Organizer entity
     */
    @Transactional
    @Override
    public Organizer assignOrUpdateOrganizer(UUID organizationId, OrganizerAssignDto organizerAssignDto) {

        Organizer source = organizerMapper.fromAssignDto(organizerAssignDto);

        Optional<Organizer> current = Optional
                .ofNullable(organizerRepository.findByOrganizationIdAndUserId(organizationId, organizerAssignDto.getUserId()));
        if (current.isPresent()) {
            Organizer toUpdate = organizerMapper.merge(current.get(), source);
            return organizerRepository.save(toUpdate);
        } else {
            source.setOrganization(organizationRepository.getById(organizationId));
            return organizerRepository.save(source);
        }
    }


}
