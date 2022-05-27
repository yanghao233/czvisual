package com.czvisual.controller;

import com.czvisual.service.HotspringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RequestMapping("/hsManage")
@Controller
public class HSManageController {
    @Autowired
    HotspringService hotspringService;

    @RequestMapping("overview")
    public String overview() {
        return "/view/hsmanage/overview.html";
    }

    @RequestMapping("tableview")
    public String tableview() {
        return "/view/hsmanage/tableview.html";
    }

    @RequestMapping("getAllHotspring")
    @ResponseBody
    public Object getAllHotspring() {
        return hotspringService.findAll();
    }


}
