package ru.meleshkin.placesandevents.domain.entity;

import lombok.Getter;
import lombok.Setter;
import ru.meleshkin.placesandevents.domain.enums.UserType;

import javax.persistence.*;

/**
 * Entity of a user data
 *
 * @author Meleshkin Alexandr
 * @since 17.01.2022
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserType type;

    @Column(name = "age")
    private Integer age;

    @Column(name = "email")
    String email;

}
