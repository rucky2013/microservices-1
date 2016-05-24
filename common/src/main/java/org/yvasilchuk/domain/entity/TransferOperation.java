package org.yvasilchuk.domain.entity;

import org.yvasilchuk.domain.model.cash.operation.OperationModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("transfer")
public class TransferOperation extends Operation {

    @ManyToOne
    @JoinColumn(name = "recipient_account", nullable = false)
    private CashAccount recipient;

    public TransferOperation(OperationModel request) {
        super(request);
    }

    public CashAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(CashAccount recipient) {
        this.recipient = recipient;
    }
}
