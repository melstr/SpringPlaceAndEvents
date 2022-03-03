package ru.meleshkin.placesandevents.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.meleshkin.placesandevents.domain.entity.User;

import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
public interface UserService {
    User get(UUID id);

    Page<User> getAll(Pageable pageable);

    User create(User user);

    User update(UUID id, User user);

    void delete(UUID id);
}
