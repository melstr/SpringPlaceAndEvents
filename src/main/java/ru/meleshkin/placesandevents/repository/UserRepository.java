package ru.meleshkin.placesandevents.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.meleshkin.placesandevents.domain.entity.User;

import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
public interface UserRepository extends JpaRepository<User, UUID> {
}
