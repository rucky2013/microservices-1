package org.yvasilchuk.domain.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("basic")
public class BasicCashOperation extends CashOperation {
    @ManyToOne
    @JoinColumn(name = "cash_account", nullable = false)
    private CashAccount account;

    public CashAccount getAccount() {
        return account;
    }

    public void setAccount(CashAccount account) {
        this.account = account;
    }
}
