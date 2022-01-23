package ru.meleshkin.placesandevents.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meleshkin.placesandevents.domain.entity.Organizer;
import ru.meleshkin.placesandevents.repository.OrganizerRepository;
import ru.meleshkin.placesandevents.service.OrganizerService;

import java.util.List;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 22.01.2022
 */
@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {

    private final OrganizerRepository organizerRepository;

    @Override
    public Organizer create(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public List<Organizer> getAll() {
        return null;
    }

    @Override
    public Organizer get() {
        return null;
    }

    @Override
    public Organizer update(UUID id, Organizer organizer) {
        return null;
    }
}
