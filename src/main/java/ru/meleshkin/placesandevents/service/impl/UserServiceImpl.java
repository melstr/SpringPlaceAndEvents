package ru.meleshkin.placesandevents.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User get(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(UUID id, User user) {
        final User current = get(id);
        final User toUpdate = userMapper.merge(current, user);
        return userRepository.save(toUpdate);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        userRepository.delete(get(id));
    }
}
