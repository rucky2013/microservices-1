package org.yvasilchuk.service;

import org.yvasilchuk.domain.entity.User;

import java.util.List;

public interface UserService {
    List<String> getServices();

    String getDbServerUrl();

    User getUserById(Integer id);
}
