package ru.meleshkin.placesandevents.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PRIVATE;


/**
 * @author Meleshkin Alexandr
 * @since 03.03.2022
 */
@Getter
@Setter
@Entity
@Table(name = "events")
public class Event extends AuditableEntity{
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "organizer_id")
    Organizer organizer;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "organization_id")
    Organization organization;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime startDateTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    LocalDateTime endDateTime;
    String title;
    String description;
}
