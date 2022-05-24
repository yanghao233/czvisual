package com.czvisual.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chartAnalysis")
@Controller
public class ChartController {
    @RequestMapping("chart")
    public String table(){
        return "/view/chart/chart.html";
    }
}
