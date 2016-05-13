package org.yvasilchuk.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "cash_operation_categories")
public class CashOperationCategory extends AbstractEntity {
    @Column(name = "name", nullable = false, updatable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    public CashOperationCategory() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
