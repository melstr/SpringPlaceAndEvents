package ru.meleshkin.placesandevents.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Objects;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * @author Meleshkin Alexandr
 * @since 17.01.2022
 */
@Getter
@Setter(value = PRIVATE)
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Version
    private Integer version;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
