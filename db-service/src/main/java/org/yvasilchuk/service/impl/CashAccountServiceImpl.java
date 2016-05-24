package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yvasilchuk.domain.entity.CashAccount;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.messages.ValidationMessages;
import org.yvasilchuk.domain.model.cash.account.CashAccountModel;
import org.yvasilchuk.exceptions.EntityNotFoundException;
import org.yvasilchuk.exceptions.InvalidRequestException;
import org.yvasilchuk.repository.CashAccountRepository;
import org.yvasilchuk.repository.UserRepository;
import org.yvasilchuk.service.CashAccountService;

import javax.transaction.Transactional;
import java.util.List;

@Service("DB_CASH_ACCOUNT_SERVICE")
@Transactional
public class CashAccountServiceImpl implements CashAccountService {
    @Autowired
    CashAccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public CashAccount get(Integer id) {
        CashAccount account = accountRepository.findOne(id);
        if (account == null) {
            throw new EntityNotFoundException(ErrorMessages.CASH_ACCOUNT_NOT_FOUND);
        }
        return account;
    }

    @Override
    public List<CashAccount> find(Integer ownerId) {
        User owner = userRepository.findOne(ownerId);
        if (owner == null) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }
        return accountRepository.findByOwner(owner);
    }

    @Override
    public CashAccount create(CashAccountModel request) {
        User owner = userRepository.findOne(request.getOwnerId());
        if (owner == null) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }

        CashAccount account = new CashAccount(request);
        account.setOwner(owner);

        account = accountRepository.save(account);
        return account;
    }

    @Override
    public CashAccount patch(CashAccountModel req) {
        if (req.getId() == null) {
            throw new InvalidRequestException(ValidationMessages.CASH_ACCOUNT_ID_REQUIRED);
        }

        CashAccount account = accountRepository.findOne(req.getId());
        if (account == null) {
            throw new EntityNotFoundException(ErrorMessages.CASH_ACCOUNT_NOT_FOUND);
        }

        if (req.getBalance() != null) {
            account.setBalance(req.getBalance());
        }

        if (req.getName() != null) {
            account.setName(req.getName());
        }

        if (req.getCurrency() != null) {
            account.setCurrency(req.getCurrency());
        }

        account = accountRepository.save(account);
        return account;
    }

    @Override
    public void delete(Integer id) {
        accountRepository.delete(id);
    }
}
