package org.yvasilchuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.CashOperationCategory;

@Repository
public interface CashOperationCategoryRepository extends JpaRepository<CashOperationCategory, Integer> {
}
