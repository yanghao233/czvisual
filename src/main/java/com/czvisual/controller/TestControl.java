package com.czvisual.controller;

import com.czvisual.entity.User;
import com.czvisual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestControl {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String hello(String username) {
        User user = userService.findUserByUsername(username);
        System.out.println("hello");
        return user.toString();
    }

}
