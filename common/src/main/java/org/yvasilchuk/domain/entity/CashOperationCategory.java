package org.yvasilchuk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.yvasilchuk.domain.enums.CashOperationCategoryType;

import javax.persistence.*;

@Entity
@Table(name = "cash_operation_categories")
public class CashOperationCategory extends AbstractEntity {
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    @JsonIgnore
    private User owner;
    @Transient
    private Integer ownerId;


    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CashOperationCategoryType type;

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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public CashOperationCategoryType getType() {
        return type;
    }

    public void setType(CashOperationCategoryType type) {
        this.type = type;
    }

    @PostLoad
    private void postLoadHandler() {
        this.ownerId = this.owner.getId();
    }
}
