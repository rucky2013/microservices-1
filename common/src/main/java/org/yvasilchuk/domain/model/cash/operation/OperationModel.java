package org.yvasilchuk.domain.model.cash.operation;

import org.yvasilchuk.domain.enums.OperationType;
import org.yvasilchuk.domain.messages.ValidationMessages;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class OperationModel implements Serializable {
    private static final long serialVersionUID = -630534266515890119L;

    private Integer id;
    @NotNull(message = ValidationMessages.OPERATION_SENDER_ACCOUNT_ID_REQUIRED)
    private Integer senderAccountId;
    @NotNull(message = ValidationMessages.OPERATION_TYPE_REQUIRED)
    private OperationType type;
    @NotNull(message = ValidationMessages.OPERATION_CATEGORY_ID_REQUIRED)
    private Integer categoryId;
    @NotNull(message = ValidationMessages.OPERATION_AMOUNT_REQUIRED)
    private Double amount;
    private Integer recipientAccountId;

    public OperationModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
