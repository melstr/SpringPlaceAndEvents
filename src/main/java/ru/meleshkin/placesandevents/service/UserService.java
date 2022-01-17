package ru.meleshkin.placesandevents.service;

import ru.meleshkin.placesandevents.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    public User get(UUID id);

    public List<User> getAll();

    public User create(User user);

    public User update(UUID id, User user);

    public void delete(UUID id);
}
