package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.messages.ErrorMessages;
import org.yvasilchuk.domain.model.enums.RegistrationType;
import org.yvasilchuk.domain.model.user.UserAccount;
import org.yvasilchuk.domain.model.user.UserProfile;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.requests.SigninRequest;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.exceptions.DatabaseException;
import org.yvasilchuk.exceptions.InternalServerException;
import org.yvasilchuk.service.UserService;
import org.yvasilchuk.services.DiscoveryService;

import java.util.HashMap;
import java.util.Map;

@Service("SERVER_USER_SERVICE")
public class UserServiceImpl implements UserService {
    @Autowired
    DiscoveryService discoveryService;

    @Override
    public User getUserById(Integer id) {
        RestTemplate template = new RestTemplate();
        User user = template.getForObject(discoveryService.getDbServerUrl() + "/api/user/" + id, User.class);
        return user;
    }

    @Override
    public UserProfile signup(RegistrationRequest request) {
        User user;
        if (RegistrationType.WEB.equals(request.getRegistrationType())) {
            user = signupInDatabase(request);
        } else {
            UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Only web registration supported");
            throw new InternalServerException(unsupportedOperationException);
        }

        return new UserProfile(user);
    }

    @Override
    public UserAccount signin(SigninRequest request) {
        User user = signinInDatabase(request);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        securityContext.setAuthentication(authenticationToken);
        SecurityContextHolder.setContext(securityContext);

        return new UserAccount(user);
    }

    @Override
    public User getLoggedInUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername executed");

        RestTemplate template = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("name", username);
        String route = discoveryService.getDbServerUrl() + "/api/user/";
        ResponseEntity<BaseResponse<User>> dbResponse = template.exchange(
                route,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BaseResponse<User>>() {
                },
                params);

        if (!dbResponse.getStatusCode().is2xxSuccessful()) {
            throw new UsernameNotFoundException(ErrorMessages.AUTHENTICATION_EXCEPTION);
        }

        User user = dbResponse.getBody().getResponse();

        System.out.println("Returns: " + user);

        return user;
    }

    private User signinInDatabase(SigninRequest request) {
        User user;
        String route = discoveryService.getDbServerUrl() + "/api/user/signin";

        HttpEntity<SigninRequest> requestEntity = new HttpEntity<>(request);

        RestTemplate template = new RestTemplate();
        ResponseEntity<BaseResponse<User>> dbResponse = template.exchange(
                route,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<BaseResponse<User>>() {
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

        user = dbResponse.getBody().getResponse();
        return user;
    }

    private User signupInDatabase(RegistrationRequest request) {
        User user;
        String signupDbServiceRoute = discoveryService.getDbServerUrl() + "/api/user/signup";

        HttpEntity<RegistrationRequest> requestEntity = new HttpEntity<>(request);

        RestTemplate template = new RestTemplate();
        ResponseEntity<BaseResponse<User>> dbResponse = template.exchange(
                signupDbServiceRoute,
                HttpMethod.POST,
                requestEntity,
                new ParameterizedTypeReference<BaseResponse<User>>() {
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

        user = dbResponse.getBody().getResponse();
        return user;
    }
}
