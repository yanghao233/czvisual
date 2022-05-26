package com.czvisual.controller;

import com.czvisual.service.DataService;
import com.czvisual.service.HotspringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/dataManage")
@Controller
public class DataManageController {
    @Autowired
    private DataService dataService;
    @Autowired
    private HotspringService hotspringService;

    @RequestMapping("hs")
    public String hs(){
        return "/view/datamanage/hs.html";
    }

    @RequestMapping("operatehsdata")
    public String operatehsdata(){
        return "/view/datamanage/operatehsdata.html";
    }

    @RequestMapping("getAllData")
    @ResponseBody
    public Object getAllData(String startDate, String endDate, String table){
        //TODO: 判断table是否存在
        return dataService.getAllData(startDate, endDate, table);
        }

    @RequestMapping("getAllHotspring")
    @ResponseBody
    public Object getAllHotspring() {
        return hotspringService.findAll();
    }
}
