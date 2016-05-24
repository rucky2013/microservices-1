package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.CashAccount;
import org.yvasilchuk.domain.model.cash.account.CashAccountModel;

import java.util.List;

public interface CashAccountService {
    CashAccount get(Integer id);

    List<CashAccount> find(Integer ownerId);

    CashAccount create(CashAccountModel request);

    CashAccount patch(CashAccountModel request);

    void delete(Integer id);

}
