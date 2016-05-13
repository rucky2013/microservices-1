package org.yvasilchuk.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("transfer")
public class TransferCashOperation extends CashOperation {
    @ManyToOne
    @JoinColumn(name = "sender_account", nullable = false)
    private CashAccount sender;

    @ManyToOne
    @JoinColumn(name = "recipient_account", nullable = false)
    private CashAccount recipient;

    public CashAccount getSender() {
        return sender;
    }

    public void setSender(CashAccount sender) {
        this.sender = sender;
    }

    public CashAccount getRecipient() {
        return recipient;
    }

    public void setRecipient(CashAccount recipient) {
        this.recipient = recipient;
    }
}
