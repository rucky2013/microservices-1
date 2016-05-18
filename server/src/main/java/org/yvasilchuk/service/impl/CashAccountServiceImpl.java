package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yvasilchuk.domain.entity.CashAccount;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.service.CashAccountService;
import org.yvasilchuk.services.DiscoveryService;

@Service("SERVER_CASH_ACCOUNT_SERVICE")
public class CashAccountServiceImpl implements CashAccountService {
    @Autowired
    DiscoveryService discoveryService;

    @Override
    public CashAccount getById(Integer accountId) {
        RestTemplate template = new RestTemplate();
        String route = discoveryService.getDbServerUrl() + "/api/accounts/" + accountId;
        ResponseEntity<BaseResponse<CashAccount>> dbResponse = template.exchange(
                route,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BaseResponse<CashAccount>>() {
                });

        if (!dbResponse.getStatusCode().is2xxSuccessful()) {
            throw new UsernameNotFoundException(ErrorMessages.AUTHENTICATION_EXCEPTION);
        }

        return dbResponse.getBody().getResponse();
    }
}
