package com.czvisual.controller;

import com.alibaba.fastjson.JSONObject;
import com.czvisual.entity.Data;
import com.czvisual.entity.Hotspring;
import com.czvisual.service.DataService;
import com.czvisual.service.HotspringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public String operatehsdata(Model model){
        List<Hotspring> hotsprings = hotspringService.findAll();
        model.addAttribute("hotstprings",hotsprings);
        model.addAttribute("edit",1);
        model.addAttribute("reset",0);
        model.addAttribute("del",0);
        return "/view/datamanage/operatehsdata.html";
    }

    @RequestMapping("getAllData")
    @ResponseBody
    public Object getAllData(String startDate, String endDate, String table){
        //TODO: 判断table是否存在
        return dataService.getAllData(startDate, endDate, table);
    }

    @RequestMapping("getRealAllData")
    @ResponseBody
    public Object getRealAllData(String table){
        JSONObject jo = new JSONObject();
        List<Data> list = dataService.getRealAllData(table);
        jo.put("code",0);
        jo.put("count", list.size());
        jo.put("msg", "成功");
        jo.put("data",dataService.getRealAllData(table));
        return jo;
    }


}
