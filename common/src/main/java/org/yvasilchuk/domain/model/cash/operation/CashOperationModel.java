package org.yvasilchuk.domain.model.cash.operation;

import org.yvasilchuk.domain.enums.OperationType;

import java.io.Serializable;

public class CashOperationModel implements Serializable {
    private static final long serialVersionUID = -630534266515890119L;

    private Integer senderAccountId;
    private OperationType type;
    private Integer categoryId;
    private Double amount;
    private Integer recipientAccountId;

    public CashOperationModel() {
    }

    public Integer getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Integer senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getRecipientAccountId() {
        return recipientAccountId;
    }

    public void setRecipientAccountId(Integer recipientAccountId) {
        this.recipientAccountId = recipientAccountId;
    }
}
