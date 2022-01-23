package ru.meleshkin.placesandevents.service;

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

    Organizer get();

    Organizer update(UUID id, Organizer organizer);

}
