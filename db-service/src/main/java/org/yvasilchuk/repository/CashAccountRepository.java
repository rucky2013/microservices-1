package org.yvasilchuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.CashAccount;
import org.yvasilchuk.domain.entity.User;

import java.util.List;

@Repository
public interface CashAccountRepository extends JpaRepository<CashAccount, Integer> {
    List<CashAccount> findByOwner(User owner);
}
