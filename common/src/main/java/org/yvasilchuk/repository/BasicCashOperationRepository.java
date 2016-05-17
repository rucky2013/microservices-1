package org.yvasilchuk.repository;

import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.BasicCashOperation;

@Repository
public interface BasicCashOperationRepository extends CashOperationBaseRepository<BasicCashOperation> {
}
