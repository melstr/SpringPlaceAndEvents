package ru.meleshkin.placesandevents.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Meleshkin Alexandr
 * @since 21.01.2022
 */
@Getter
@Setter
@Entity
@Table(name = "organizations")
public class Organization extends AuditableEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

}
