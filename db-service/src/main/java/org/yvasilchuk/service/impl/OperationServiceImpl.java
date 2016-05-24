package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yvasilchuk.domain.entity.*;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.messages.ValidationMessages;
import org.yvasilchuk.domain.model.cash.operation.OperationModel;
import org.yvasilchuk.exceptions.EntityNotFoundException;
import org.yvasilchuk.exceptions.InvalidRequestException;
import org.yvasilchuk.repository.*;
import org.yvasilchuk.service.OperationService;

import javax.transaction.Transactional;

@Service("DB_OPERATION_SERVICE")
@Transactional
public class OperationServiceImpl implements OperationService {
    @Autowired
    OperationRepository operationRepository;
    @Autowired
    BasicOperationRepository basicOperationRepository;
    @Autowired
    TransferOperationRepository transferOperationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CashAccountRepository accountRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Operation create(OperationModel request) {
        switch (request.getType()) {
            case ADD:
            case SUBTRACT:
                return createBaseOperation(request);
            case TRANSFER:
                return createTransferOperation(request);
            default:
                throw new InvalidRequestException(ErrorMessages.UNSUPPORTED_CASH_OPERATION);
        }
    }

    private TransferOperation createTransferOperation(OperationModel request) {
        TransferOperation operation = new TransferOperation(request);
        if (request.getRecipientAccountId() == null) {
            throw new InvalidRequestException(ValidationMessages.OPERATION_RECIPIENT_ACCOUNT_ID_REQUIRED);
        }
        CashAccount recipientAccount = accountRepository.findOne(request.getRecipientAccountId());
        if (recipientAccount == null) {
            throw new EntityNotFoundException(ErrorMessages.CASH_ACCOUNT_RECIPIENT_NOT_FOUND);
        }

        initOperation(request, operation);

        operation = transferOperationRepository.save(operation);
        return operation;
    }

    private BasicOperation createBaseOperation(OperationModel request) {
        BasicOperation operation = new BasicOperation(request);
        initOperation(request, operation);
        operation = basicOperationRepository.save(operation);
        return operation;
    }

    private void initOperation(OperationModel request, Operation operation) {
        CashAccount senderAccount = accountRepository.findOne(request.getSenderAccountId());
        if (senderAccount == null) {
            throw new EntityNotFoundException(ErrorMessages.CASH_ACCOUNT_SENDER_NOT_FOUND);
        }
        operation.setSenderAccount(senderAccount);

        Category category = categoryRepository.findOne(request.getCategoryId());
        if (category == null) {
            throw new EntityNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND);
        }
        operation.setCategory(category);
    }

    @Override
    public Operation patch(OperationModel request) {
        if (request.getId() == null) {
            throw new InvalidRequestException(ValidationMessages.OPERATION_ID_REQUIRED);
        }
        Operation operation = operationRepository.findOne(request.getId());
        if (operation == null) {
            throw new EntityNotFoundException(ErrorMessages.OPERATION_NOT_FOUND);
        }

        Category category = categoryRepository.findOne(request.getCategoryId());
        if (category == null) {
            throw new EntityNotFoundException(ErrorMessages.CATEGORY_NOT_FOUND);
        }

        operation.setCategory(category);
        return operationRepository.save(operation);
    }

    @Override
    public void delete(Integer id) {
        operationRepository.delete(id);
    }

    @Override
    public Operation get(Integer id) {
        return operationRepository.findOne(id);
    }
}
