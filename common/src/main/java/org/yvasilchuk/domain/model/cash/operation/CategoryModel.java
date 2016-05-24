package org.yvasilchuk.domain.model.cash.operation;

import org.yvasilchuk.domain.entity.Category;
import org.yvasilchuk.domain.enums.CashOperationCategoryType;
import org.yvasilchuk.domain.messages.ValidationMessages;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class CategoryModel implements Serializable {
    private static final long serialVersionUID = -4149079196615555494L;

    private Integer id;
    @NotNull(message = ValidationMessages.CATEGORY_NAME_REQUIRED)
    private String name;
    @NotNull(message = ValidationMessages.CATEGORY_OWNER_ID_REQUIRED)
    private Integer ownerId;
    @NotNull(message = ValidationMessages.CATEGORY_TYPE_REQUIRED)
    private CashOperationCategoryType type;

    public CategoryModel() {
    }

    public CategoryModel(Category c) {
        this.id = c.getId();
        this.name = c.getName();
        if (c.getOwner() != null) {
            this.ownerId = c.getOwner().getId();
        }
        this.type = c.getType();
    }

    public CategoryModel(Category c, Integer ownerId) {
        this.id = c.getId();
        this.name = c.getName();
        this.ownerId = ownerId;
        this.type = c.getType();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
