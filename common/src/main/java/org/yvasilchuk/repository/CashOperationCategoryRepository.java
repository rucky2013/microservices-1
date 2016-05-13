package org.yvasilchuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yvasilchuk.domain.entity.CashOperationCategory;

public interface CashOperationCategoryRepository extends JpaRepository<CashOperationCategory, Integer> {
}
