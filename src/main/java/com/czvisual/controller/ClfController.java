package com.czvisual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ClfController {
    @RequestMapping("/clf")
    public String doo() {
        return "view/login";
    }
}
