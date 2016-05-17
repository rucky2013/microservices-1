package org.yvasilchuk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yvasilchuk.domain.entity.User;
import org.yvasilchuk.domain.model.UserProfile;
import org.yvasilchuk.domain.model.security.RoleNames;
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
        return new ResponseEntity<>(new BaseResponse<>("secure pong"), HttpStatus.OK);
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<UserProfile>> signup(@RequestBody RegistrationRequest request) {
        UserProfile profile = userService.signup(request);
        return new ResponseEntity<>(new BaseResponse<>(profile), HttpStatus.OK);
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<BaseResponse<UserProfile>> signin(@RequestBody SigninRequest request) {
        UserProfile profile = userService.signin(request);
        return new ResponseEntity<>(new BaseResponse<>(profile), HttpStatus.OK);
    }

    @RequestMapping(value = "/test-security", method = RequestMethod.GET)
    public ResponseEntity<BaseResponse<UserProfile>> testSecurity() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println("t1");
        if (securityContext != null) {
            System.out.println("t2");
            Authentication authentication = securityContext.getAuthentication();
            if (authentication != null) {
                System.out.println("t2.5");
                Object principal = authentication.getPrincipal();
                if (principal != null) {
                    System.out.println("t3");
                    return new ResponseEntity<>(new BaseResponse<>(new UserProfile((User) principal)), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
