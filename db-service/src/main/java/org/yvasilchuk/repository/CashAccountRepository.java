package org.yvasilchuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.CashAccount;

@Repository
public interface CashAccountRepository extends JpaRepository<CashAccount, Integer> {
}
