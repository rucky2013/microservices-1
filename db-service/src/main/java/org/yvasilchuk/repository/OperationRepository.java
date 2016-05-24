package org.yvasilchuk.repository;

import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.Operation;

@Repository
public interface OperationRepository extends CashOperationBaseRepository<Operation> {
}
