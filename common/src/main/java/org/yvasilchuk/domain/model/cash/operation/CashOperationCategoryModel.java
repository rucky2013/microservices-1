package org.yvasilchuk.domain.model.cash.operation;

import org.yvasilchuk.domain.entity.CashOperationCategory;
import org.yvasilchuk.domain.enums.CashOperationCategoryType;

import java.io.Serializable;

public class CashOperationCategoryModel implements Serializable {
    private static final long serialVersionUID = -4149079196615555494L;

    private String name;
    private Integer ownerId;
    private CashOperationCategoryType type;

    public CashOperationCategoryModel() {
    }

    public CashOperationCategoryModel(CashOperationCategory c) {
        this.name = c.getName();
        this.ownerId = c.getOwner().getId();
        this.type = c.getType();
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
