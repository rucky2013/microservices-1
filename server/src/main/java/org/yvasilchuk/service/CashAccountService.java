package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.CashAccount;

public interface CashAccountService {
    CashAccount getById(Integer accountId);
}
