package com.czvisual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hsManage")
@Controller
public class HSManageController {
    @RequestMapping("overview")
    public String overview(){
        return "/view/hsmanage/overview.html";
    }

    @RequestMapping("tableview")
    public String tableview(){
        return "/view/hsmanage/tableview.html";
    }
}
