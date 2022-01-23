package ru.meleshkin.placesandevents.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.meleshkin.placesandevents.domain.entity.User;
import ru.meleshkin.placesandevents.exception.UserNotFoundException;
import ru.meleshkin.placesandevents.mapper.UserMapper;
import ru.meleshkin.placesandevents.repository.UserRepository;
import ru.meleshkin.placesandevents.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 17.01.2022
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private UserMapper userMapper;

    @Override
    public User get(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> getAll() {

        return new ArrayList<>(userRepository.findAll());
    }

    @Override
    public User create(User user) {

        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        final User current = get(id);
        final User toUpdate = userMapper.merge(current, user);
        return userRepository.save(toUpdate);
    }

    @Override
    public void delete(UUID id) {
        userRepository.delete(get(id));
    }
}
