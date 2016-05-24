package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yvasilchuk.domain.entity.Category;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.service.CashOperationCategoryService;
import org.yvasilchuk.services.DiscoveryService;

@Service("SERVER_CASH_OPERATION_CATEGORY_SERVICE")
public class CashOperationCategoryServiceImpl implements CashOperationCategoryService {
    @Autowired
    DiscoveryService discoveryService;

    @Override
    public Category getById(Integer categoryId) {
        String route = discoveryService.getDbServerUrl() + "/api/data/category/" + categoryId;

        RestTemplate template = new RestTemplate();
        ResponseEntity<Category> dbResponse = template.exchange(
                route,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Category>() {
                }
        );

        HttpStatus dbResponseStatusCode = dbResponse.getStatusCode();
        if (!dbResponse.getStatusCode().is2xxSuccessful()) {
            throw new UsernameNotFoundException(ErrorMessages.DB_INTERNAL_ERROR);
        }

        return dbResponse.getBody();
    }
}
