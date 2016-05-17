package org.yvasilchuk.repository;

import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.TransferCashOperation;

@Repository
public interface TransferCashOperationRepository extends CashOperationBaseRepository<TransferCashOperation> {
}
