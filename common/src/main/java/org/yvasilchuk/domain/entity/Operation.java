package org.yvasilchuk.domain.entity;

import org.yvasilchuk.domain.enums.OperationType;
import org.yvasilchuk.domain.model.cash.operation.OperationModel;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "operation_class")
@Table(name = "cash_operations")
public abstract class Operation extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_account_id", nullable = false)
    private CashAccount senderAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private OperationType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "amount", nullable = false)
    private Double amount;

    public Operation() {
    }

    public Operation(OperationModel request) {
        this.type = request.getType();
        this.amount = request.getAmount();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public CashAccount getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(CashAccount senderAccount) {
        this.senderAccount = senderAccount;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
