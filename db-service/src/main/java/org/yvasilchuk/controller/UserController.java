package org.yvasilchuk.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.model.user.UserProfile;
import org.yvasilchuk.domain.model.user.UserSearchModel;
import org.yvasilchuk.domain.requests.RegistrationRequest;
import org.yvasilchuk.domain.requests.SigninRequest;
import org.yvasilchuk.domain.response.BaseResponse;
import org.yvasilchuk.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    @Qualifier("DB_USER_SERVICE")
    UserService userService;

    @ApiOperation(value = "signup", nickname = "signup")
    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public ResponseEntity<BaseResponse<User>> signup(@RequestBody RegistrationRequest request) {
        User user = userService.signup(request);
        return new ResponseEntity<>(new BaseResponse<>(user), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signin")
    public ResponseEntity<BaseResponse<User>> signin(@RequestBody SigninRequest request) {
        User user = userService.signin(request);
        return new ResponseEntity<>(new BaseResponse<>(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<List<User>>> findUsers(UserSearchModel requestModel) {
        List<User> result = userService.find(requestModel);
        return new ResponseEntity<>(new BaseResponse<>(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<User>> getUser(UserSearchModel requestModel) {
        User result = userService.get(requestModel);
        return new ResponseEntity<>(new BaseResponse<>(result), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<BaseResponse<User>> updateUser(@RequestBody UserProfile request) {
        User user = userService.updateUser(request);
        return new ResponseEntity<>(new BaseResponse<>(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public ResponseEntity<BaseResponse<User>> patchUser(@RequestBody UserProfile request) {
        User user = userService.patchUser(request);
        return new ResponseEntity<>(new BaseResponse<>(user), HttpStatus.OK);
    }
}
