package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/ping")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }

    @RequestMapping(value = "/service")
    public ResponseEntity<List<String>> getServices() {
        List<String> services = userService.getServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @RequestMapping(value = "/service/db/uri")
    public ResponseEntity<String> getDbServiceUri() {
        String dbServerUrl = userService.getDbServerUrl();
        return new ResponseEntity<>(dbServerUrl, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
