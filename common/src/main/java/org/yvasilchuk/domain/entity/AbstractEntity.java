package org.yvasilchuk.domain.entity;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractEntity extends IntegerIdEntity {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @PrePersist
    private void prePersistHandler() {
        this.created = new Date();
        this.updated = new Date();
    }

    @PreUpdate
    private void preUpdateHandler() {
        this.updated = new Date();
    }
}
