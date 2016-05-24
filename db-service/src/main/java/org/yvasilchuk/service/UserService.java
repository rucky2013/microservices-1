package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.model.user.UserProfile;
import org.yvasilchuk.domain.model.user.UserSearchModel;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.requests.SigninRequest;

import java.util.List;

public interface UserService {
    User signup(RegistrationRequest request);

    User signin(SigninRequest request);

    List<User> find(UserSearchModel request);

    User get(UserSearchModel request);

    User updateUser(UserProfile request);

    User patchUser(UserProfile request);

    User get(Integer id);
}
