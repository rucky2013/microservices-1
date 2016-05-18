package org.yvasilchuk.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.model.user.UserAccount;
import org.yvasilchuk.domain.model.user.UserProfile;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.requests.SigninRequest;

public interface UserService extends UserDetailsService {
    User getUserById(Integer id);

    UserProfile signup(RegistrationRequest request);

    UserAccount signin(SigninRequest request);

    User getLoggedInUser();
}
