package ru.meleshkin.placesandevents.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.time.LocalDateTime;
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

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "modified_at", columnDefinition = "TIMESTAMP")
    private LocalDateTime modifiedAt;

    @PrePersist
    public void onPrePersist() {
        setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        setModifiedAt(LocalDateTime.now());
    }
}
