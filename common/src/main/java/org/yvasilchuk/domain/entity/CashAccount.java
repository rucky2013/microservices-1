package org.yvasilchuk.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.yvasilchuk.domain.enums.Currency;

import javax.persistence.*;

@Entity
@Table(name = "cash_accounts")
public class CashAccount extends AbstractEntity {
    @Column(name = "balance")
    private Long balance;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    @JsonIgnore
    private User owner;
    @Transient
    private Integer ownerId;

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    @PostLoad
    private void postLoadHandler() {
        this.ownerId = this.owner.getId();
    }
}
