package ru.meleshkin.placesandevents.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author Meleshkin Alexandr
 * @since 23.01.2022
 */
@ResponseStatus(NOT_FOUND)
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(UUID id){
        super("User not found with id = " + id);
    }
}
