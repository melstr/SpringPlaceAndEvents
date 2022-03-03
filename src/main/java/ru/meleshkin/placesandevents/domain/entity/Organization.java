package ru.meleshkin.placesandevents.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

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
    String name;

    @Column(name = "description")
    String description;

    @Setter(PRIVATE)
    @OneToMany(mappedBy = "organization",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    List<Event> events;

    @OneToMany(mappedBy = "organization",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    List<Organizer> organizers;

}
