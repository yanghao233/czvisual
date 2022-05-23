package com.czvisual.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/unAuthorized")
    public String unAuthorized(){
        return "非法访问";
    }

}
