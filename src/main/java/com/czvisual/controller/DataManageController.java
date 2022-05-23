package com.czvisual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dataManage")
@Controller
public class DataManageController {
    @RequestMapping("hs")
    public String hs(){
        return "/view/datamanage/hs.html";
    }

    @RequestMapping("operatehsdata")
    public String operatehsdata(){
        return "/view/datamanage/operatehsdata.html";
    }
}
