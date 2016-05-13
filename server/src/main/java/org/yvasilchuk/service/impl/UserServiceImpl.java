package org.yvasilchuk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.model.UserProfile;
import org.yvasilchuk.domain.model.enums.RegistrationType;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.exceptions.FatException;
import org.yvasilchuk.service.UserService;
import org.yvasilchuk.services.DiscoveryService;

@Service
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
        User user = new User();
        if (RegistrationType.WEB.equals(request.getRegistrationType())) {

        } else {
            UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Only web re");
            throw new FatException(unsupportedOperationException);
        }
    }
}
