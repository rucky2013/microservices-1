package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.model.UserProfile;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.requests.SigninRequest;

public interface UserService {
    User getUserById(Integer id);

    UserProfile signup(RegistrationRequest request);

    UserProfile signin(SigninRequest request);
}
