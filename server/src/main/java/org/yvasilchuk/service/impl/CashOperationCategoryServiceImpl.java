package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yvasilchuk.domain.entity.CashOperationCategory;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.exceptions.DatabaseException;
import org.yvasilchuk.exceptions.InternalServerException;
import org.yvasilchuk.service.CashOperationCategoryService;
import org.yvasilchuk.services.DiscoveryService;

@Service("SERVER_CASH_OPERATION_CATEGORY_SERVICE")
public class CashOperationCategoryServiceImpl implements CashOperationCategoryService {
    @Autowired
    DiscoveryService discoveryService;

    @Override
    public CashOperationCategory getById(Integer categoryId) {
        String route = discoveryService.getDbServerUrl() + "/api/category/" + categoryId;

        RestTemplate template = new RestTemplate();
        ResponseEntity<BaseResponse<CashOperationCategory>> dbResponse = template.exchange(
                route,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BaseResponse<CashOperationCategory>>() {
                }
        );

        HttpStatus dbResponseStatusCode = dbResponse.getStatusCode();
        if (!dbResponseStatusCode.is2xxSuccessful()) {
            if (dbResponseStatusCode.is4xxClientError()) {
                throw new DatabaseException(dbResponse.getBody().getErrorMessage());
            } else {
                throw new InternalServerException(ErrorMessages.DB_INTERNAL_ERROR);
            }
        }

        return dbResponse.getBody().getResponse();
    }
}
