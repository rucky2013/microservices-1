package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.Operation;
import org.yvasilchuk.domain.model.cash.operation.OperationModel;

public interface CashOperationService {
    Operation makeCashOperation(OperationModel request);
}
