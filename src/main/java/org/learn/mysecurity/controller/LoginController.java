package org.learn.mysecurity.controller;

import org.learn.mysecurity.model.User;
import org.learn.mysecurity.response.ResponseResult;
import org.learn.mysecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wqg on 2023/1/6.
 */
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/user/login")
    public ResponseResult<Map<String, String>> login(@RequestBody User user) {
        return loginService.login(user);
    }

    @GetMapping("/user/logout")
    public ResponseResult<Object> logout() {
        return loginService.logout();
    }

}
