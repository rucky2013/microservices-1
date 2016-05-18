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
import org.yvasilchuk.domain.entity.CashAccount;
import org.yvasilchuk.domain.entity.CashOperation;
import org.yvasilchuk.domain.entity.CashOperationCategory;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.model.cash.operation.CashOperationModel;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.exceptions.AuthenticationException;
import org.yvasilchuk.exceptions.DatabaseException;
import org.yvasilchuk.exceptions.InternalServerException;
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
        checkPermissions(request);
        CashOperation operation = sendCashOperationToDb(request);

        return operation;
    }

    private CashOperation sendCashOperationToDb(CashOperationModel request) {
        RestTemplate template = new RestTemplate();

        String route = discoveryService.getDbServerUrl() + "/api/operation";

        HttpEntity<Object> requestEntity = new HttpEntity<>(request);

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

        CashOperation cashOperation = dbResponse.getBody().getResponse();

        System.out.println("Returns: " + cashOperation);

        return cashOperation;
    }

    private void checkPermissions(CashOperationModel request) {
        User user = userService.getLoggedInUser();
        checkCashAccountPermissions(request.getSenderAccountId(), user);
        checkCashOperationCategoryPermissions(request.getCategoryId(), user);
    }

    private void checkCashOperationCategoryPermissions(Integer categoryId, User user) {
        CashOperationCategory category = categoryService.getById(categoryId);
        if (!category.getOwner().getId().equals(user.getId())) {
            throw new AuthenticationException(ErrorMessages.ACCESS_DENIED);
        }
    }

    private void checkCashAccountPermissions(Integer accountId, User user) {
        CashAccount account = accountService.getById(accountId);
        if (!account.getOwner().getId().equals(user.getId())) {
            throw new AuthenticationException(ErrorMessages.ACCESS_DENIED);
        }
    }
}
