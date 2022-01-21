package ru.meleshkin.placesandevents.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Date;

/**
 * TODO:
 * C внедрением Security добавить CreatedBy, ModifiedBy
 *
 * @author Meleshkin Alexandr
 * @since 21.01.2022
 */
@Getter
@Setter
@MappedSuperclass
public abstract class AuditableEntity extends BaseEntity{

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    private Date modifiedAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt((new Date()));
    }

    @PreUpdate
    public void onPreUpdate() {
        setModifiedAt((new Date()));
    }
}
