package ru.meleshkin.placesandevents.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meleshkin.placesandevents.domain.entity.Organizer;
import ru.meleshkin.placesandevents.mapper.OrganizerMapper;
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
    private final OrganizerMapper organizerMapper;

    @Override
    public Organizer create(Organizer organizer) {

        return organizerRepository.save(organizer);
    }

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

    @Override
    public Organizer update(UUID id, Organizer organizer) {

        final Organizer current = get(id);
        final Organizer toUpdate = organizerMapper.merge(current, organizer);
        return organizerRepository.save(toUpdate);
    }
}
