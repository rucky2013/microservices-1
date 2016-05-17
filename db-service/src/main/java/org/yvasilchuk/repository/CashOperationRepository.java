package org.yvasilchuk.repository;

import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.CashOperation;

@Repository
public interface CashOperationRepository extends CashOperationBaseRepository<CashOperation> {
}
