package org.yvasilchuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.yvasilchuk.domain.entity.Operation;

@NoRepositoryBean
public interface CashOperationBaseRepository<T extends Operation> extends JpaRepository<T, Integer> {
}
