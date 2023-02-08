package org.learn.mysecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wqg on 2023/1/4.
 */
@RestController
public class HelloController {


    @RequestMapping(value = "/hello")
    @PreAuthorize("hasAuthority('wqg')")
    public String hello() {
        return "hello";
    }

}
