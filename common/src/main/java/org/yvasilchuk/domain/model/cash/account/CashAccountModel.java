package org.yvasilchuk.domain.model.cash.account;

import org.yvasilchuk.domain.entity.CashAccount;
import org.yvasilchuk.domain.enums.Currency;

import java.io.Serializable;

public class CashAccountModel implements Serializable {
    private static final long serialVersionUID = -4508018399018354870L;

    private Integer id;
    private Long balance;
    private String name;
    private Currency currency;
    private Integer ownerId;

    public CashAccountModel() {
    }

    public CashAccountModel(CashAccount ca) {
        this.id = ca.getId();
        this.balance = ca.getBalance();
        this.name = ca.getName();
        this.currency = ca.getCurrency();
        if (ca.getOwner() != null) {
            this.ownerId = ca.getOwner().getId();
        }
    }

    public CashAccountModel(CashAccount ca, Integer ownerId) {
        this.id = ca.getId();
        this.balance = ca.getBalance();
        this.name = ca.getName();
        this.currency = ca.getCurrency();
        this.ownerId = ownerId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }
}
