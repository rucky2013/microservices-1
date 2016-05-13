package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yvasilchuk.repository.UserRepository;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;
}
