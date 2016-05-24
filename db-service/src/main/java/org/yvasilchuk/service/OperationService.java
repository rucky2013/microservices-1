package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.Operation;
import org.yvasilchuk.domain.model.cash.operation.OperationModel;

public interface OperationService {
    Operation create(OperationModel request);

    Operation patch(OperationModel request);

    void delete(Integer id);

    Operation get(Integer id);
}
