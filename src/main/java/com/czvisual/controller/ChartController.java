package com.czvisual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chartAnalysis")
@Controller
public class ChartController {
    @RequestMapping("wbgt")
    public String wbgt(){
        return "/view/hsmanage/overview.html";
    }

    @RequestMapping("zzmsd")
    public String zzmsd(){
        return "/view/hsmanage/tableview.html";
    }
    @RequestMapping("hjwd")
    public String hjwd(){
        return "/view/hsmanage/tableview.html";
    }
    @RequestMapping("zzmwd")
    public String zzmwd(){
        return "/view/hsmanage/tableview.html";
    }
    @RequestMapping("zzmlc")
    public String zzmlc(){
        return "/view/hsmanage/tableview.html";
    }
}
