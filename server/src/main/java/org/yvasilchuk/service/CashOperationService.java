package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.CashOperation;
import org.yvasilchuk.domain.model.cash.operation.CashOperationModel;

public interface CashOperationService {
    CashOperation makeCashOperation(CashOperationModel request);
}
