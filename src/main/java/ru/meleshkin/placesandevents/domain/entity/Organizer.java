package ru.meleshkin.placesandevents.domain.entity;

import lombok.Getter;
import lombok.Setter;
import ru.meleshkin.placesandevents.domain.enums.OrganizerRole;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

/**
 * @author Meleshkin Alexandr
 * @since 22.01.2022
 */

@Getter
@Setter
@Entity
@Table(name = "organizers")
public class Organizer extends BaseEntity {

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "organization_id")
    Organization organization;

    @Enumerated(STRING)
    @Column(name = "role")
    OrganizerRole role;


}
