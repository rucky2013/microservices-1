package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yvasilchuk.domain.model.security.RoleNames;
import org.yvasilchuk.domain.model.user.UserAccount;
import org.yvasilchuk.domain.model.user.UserProfile;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.requests.SigninRequest;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    @Qualifier("SERVER_USER_SERVICE")
    UserService userService;

    @RequestMapping(value = "/ping")
    public ResponseEntity<BaseResponse<String>> ping() {
        return new ResponseEntity<>(new BaseResponse<>("pong"), HttpStatus.OK);
    }

    @RequestMapping(value = "/secure-ping")
    @Secured({RoleNames.ROLE_USER, RoleNames.ROLE_ADMIN})
    public ResponseEntity<BaseResponse<String>> securePing() {
        return new ResponseEntity<>(new BaseResponse<>("pong"), HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<UserProfile>> signup(@RequestBody RegistrationRequest request) {
        UserProfile profile = userService.signup(request);
        return new ResponseEntity<>(new BaseResponse<>(profile), HttpStatus.OK);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<UserAccount>> signin(@RequestBody SigninRequest request) {
        UserAccount profile = userService.signin(request);
        return new ResponseEntity<>(new BaseResponse<>(profile), HttpStatus.OK);
    }


}
