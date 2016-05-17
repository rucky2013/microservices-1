package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.requests.SigninRequest;

public interface UserService {
    User signup(RegistrationRequest request);

    User signin(SigninRequest request);
}
