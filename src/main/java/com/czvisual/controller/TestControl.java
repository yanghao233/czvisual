package com.czvisual.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestControl {
    @RequestMapping("/")
    public String hello() {
        return "hello world";
    }

}
