package org.yvasilchuk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.yvasilchuk.domain.enums.CashOperationCategoryType;
import org.yvasilchuk.domain.model.cash.operation.CategoryModel;

import javax.persistence.*;

@Entity
@Table(name = "cash_operation_categories")
public class Category extends AbstractEntity {
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    @JsonIgnore
    private User owner;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CashOperationCategoryType type;

    public Category() {
    }

    public Category(CategoryModel c) {
        this.name = c.getName();
        this.type = c.getType();
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

    public CashOperationCategoryType getType() {
        return type;
    }

    public void setType(CashOperationCategoryType type) {
        this.type = type;
    }
}
