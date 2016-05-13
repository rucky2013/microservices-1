package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yvasilchuk.domain.model.UserProfile;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/ping")
    public ResponseEntity<BaseResponse<String>> hello() {
        return new ResponseEntity<>(new BaseResponse<>("pong"), HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<UserProfile>> signup(@RequestBody RegistrationRequest request) {
        UserProfile profile = userService.signup(request);
        return new ResponseEntity<>(new BaseResponse<>(profile), HttpStatus.OK);
    }


}
