package ru.meleshkin.placesandevents.service;

import ru.meleshkin.placesandevents.domain.entity.User;

import java.util.List;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
public interface UserService {
    User get(UUID id);

    List<User> getAll();

    User create(User user);

    User update(UUID id, User user);

    void delete(UUID id);
}
