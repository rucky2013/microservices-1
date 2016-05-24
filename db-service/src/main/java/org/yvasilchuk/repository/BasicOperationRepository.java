package org.yvasilchuk.repository;

import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.BasicOperation;

@Repository
public interface BasicOperationRepository extends CashOperationBaseRepository<BasicOperation> {
}
