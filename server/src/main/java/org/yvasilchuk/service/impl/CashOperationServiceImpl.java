package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yvasilchuk.domain.entity.*;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.model.cash.operation.CashOperationModel;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.exceptions.AuthenticationException;
import org.yvasilchuk.exceptions.DatabaseException;
import org.yvasilchuk.exceptions.InternalServerException;
import org.yvasilchuk.exceptions.InvalidRequestException;
import org.yvasilchuk.service.CashAccountService;
import org.yvasilchuk.service.CashOperationCategoryService;
import org.yvasilchuk.service.CashOperationService;
import org.yvasilchuk.service.UserService;
import org.yvasilchuk.services.DiscoveryService;

@Service("SERVER_CASH_OPERATION_SERVICE")
public class CashOperationServiceImpl implements CashOperationService {
    @Autowired
    DiscoveryService discoveryService;
    @Autowired
    @Qualifier("SERVER_CASH_OPERATION_CATEGORY_SERVICE")
    CashOperationCategoryService categoryService;
    @Autowired
    @Qualifier("SERVER_CASH_ACCOUNT_SERVICE")
    CashAccountService accountService;
    @Autowired
    @Qualifier("SERVER_USER_SERVICE")
    UserService userService;

    @Override
    public CashOperation makeCashOperation(CashOperationModel request) {
        CashOperationCategory category = categoryService.getById(request.getCategoryId());
        CashAccount account = accountService.getById(request.getSenderAccountId());
        User user = userService.getLoggedInUser();

        checkCashOperationCategoryPermissions(user, category);
        checkCashAccountPermissions(user, account);

        CashOperation operation = sendCashOperationToDb(request, user, account, category);

        return operation;
    }

    private CashOperation sendCashOperationToDb(CashOperationModel request, User user, CashAccount account, CashOperationCategory category) {
        CashOperation cashOperation;
        switch (request.getType()) {
            case ADD:
            case SUBTRACT:
                cashOperation = sendBasicCashOperationToDb(request, user, account, category);
                break;
            case TRANSFER:
                CashAccount recipient = accountService.getById(request.getRecipientAccountId());
                checkCashAccountPermissions(user, recipient);
                cashOperation = sendTransferCashOperationToDb(request, user, account, recipient, category);
                break;
            default:
                throw new InvalidRequestException(ErrorMessages.UNSUPPORTED_CASH_OPERATION);
        }

        return cashOperation;
    }

    private CashOperation sendTransferCashOperationToDb(CashOperationModel request, User user, CashAccount sender, CashAccount recipient, CashOperationCategory category) {
        TransferCashOperation operation = new TransferCashOperation();

        operation.setOwner(user);
        operation.setCategory(category);
        operation.setSenderAccount(sender);
        operation.setRecipient(recipient);

        operation.setType(request.getType());
        operation.setAmount(request.getAmount());

        RestTemplate template = new RestTemplate();

        String route = discoveryService.getDbServerUrl() + "/api/data/transfer-operation";

        HttpEntity<TransferCashOperation> requestEntity = new HttpEntity<>(operation);

        ResponseEntity<BaseResponse<CashOperation>> dbResponse = template.exchange(
                route,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<BaseResponse<CashOperation>>() {
                });

        HttpStatus dbResponseStatusCode = dbResponse.getStatusCode();
        if (!dbResponseStatusCode.is2xxSuccessful()) {
            if (dbResponseStatusCode.is4xxClientError()) {
                throw new DatabaseException(dbResponse.getBody().getErrorMessage());
            } else {
                throw new InternalServerException(ErrorMessages.DB_INTERNAL_ERROR);
            }
        }

        return operation;
    }

    private CashOperation sendBasicCashOperationToDb(CashOperationModel request, User user, CashAccount account, CashOperationCategory category) {
        BasicCashOperation operation = new BasicCashOperation();

        operation.setOwner(user);
        operation.setCategory(category);
        operation.setSenderAccount(account);

        operation.setType(request.getType());
        operation.setAmount(request.getAmount());

        RestTemplate template = new RestTemplate();

        String route = discoveryService.getDbServerUrl() + "/api/data/basic-operation";

        HttpEntity<BasicCashOperation> requestEntity = new HttpEntity<>(operation);

        ResponseEntity<BaseResponse<CashOperation>> dbResponse = template.exchange(
                route,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<BaseResponse<CashOperation>>() {
                });

        HttpStatus dbResponseStatusCode = dbResponse.getStatusCode();
        if (!dbResponseStatusCode.is2xxSuccessful()) {
            if (dbResponseStatusCode.is4xxClientError()) {
                throw new DatabaseException(dbResponse.getBody().getErrorMessage());
            } else {
                throw new InternalServerException(ErrorMessages.DB_INTERNAL_ERROR);
            }
        }

        return operation;
    }

//    private void checkPermissions(CashOperationModel request) {
//        User user = userService.getLoggedInUser();
//        checkCashAccountPermissions(request.getSenderAccountId(), user);
//        checkCashOperationCategoryPermissions(request.getCategoryId(), user);
//    }

//    private void checkCashOperationCategoryPermissions(Integer categoryId, User user) {
//        CashOperationCategory category = categoryService.getById(categoryId);
//        checkCashOperationCategoryPermissions(user, category);
//    }

    private void checkCashOperationCategoryPermissions(User user, CashOperationCategory category) {
        if (!category.getOwnerId().equals(user.getId())) {
            throw new AuthenticationException(ErrorMessages.ACCESS_DENIED);
        }
    }

//    private void checkCashAccountPermissions(Integer accountId, User user) {
//        CashAccount account = accountService.getById(accountId);
//        checkCashAccountPermissions(user, account);
//    }

    private void checkCashAccountPermissions(User user, CashAccount account) {
        if (!account.getOwnerId().equals(user.getId())) {
            throw new AuthenticationException(ErrorMessages.ACCESS_DENIED);
        }
    }
}
