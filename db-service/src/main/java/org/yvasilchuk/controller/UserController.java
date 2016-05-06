package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
        User user = userRepository.findOne(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getUser(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "name", required = false) String name
    ) {
        if (id != null) {
            User user = userRepository.findOne(id);
            List<User> result = Collections.singletonList(user);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (name != null) {
            List<User> users = userRepository.findByName(name);
            return new ResponseEntity<>(users, HttpStatus.OK);
        } else {
            List<User> users = userRepository.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> insertUser(@RequestBody User user) {
        user = userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
