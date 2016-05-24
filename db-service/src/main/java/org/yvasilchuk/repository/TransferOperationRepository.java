package org.yvasilchuk.repository;

import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.TransferOperation;

@Repository
public interface TransferOperationRepository extends CashOperationBaseRepository<TransferOperation> {
}
