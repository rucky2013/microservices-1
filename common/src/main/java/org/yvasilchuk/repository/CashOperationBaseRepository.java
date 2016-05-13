package org.yvasilchuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.yvasilchuk.domain.entity.CashOperation;

@NoRepositoryBean
public interface CashOperationBaseRepository<T extends CashOperation> extends JpaRepository<T, Integer> {
}
