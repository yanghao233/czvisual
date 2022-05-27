package com.czvisual.controller;

import com.alibaba.fastjson.JSONObject;
import com.czvisual.entity.Data;
import com.czvisual.entity.Hotspring;
import com.czvisual.entity.User;
import com.czvisual.service.DataService;
import com.czvisual.service.HotspringService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/dataManage")
@Controller
public class DataManageController {
    @Autowired
    private DataService dataService;
    @Autowired
    private HotspringService hotspringService;



    @RequestMapping("operatehsdata")
    public String operatehsdata(Model model){
        List<Hotspring> hotsprings = hotspringService.findAll();
        if(((User)SecurityUtils.getSubject().getPrincipal()).getType() == 0){
            model.addAttribute("edit",1);
            model.addAttribute("del",1);
        }
        model.addAttribute("hotstprings",hotsprings);

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
    public Object getRealAllData(String table, @RequestParam Integer page, @RequestParam Integer limit){
        JSONObject jo = new JSONObject();
        List<Data> list = dataService.getRealAllData(table);
        List<Data> subList = new ArrayList<>(limit);
        for (int i = 0; i < limit && (page - 1) * limit + i < list.size(); i++) {
            Data u = list.get((page - 1) * limit + i);
            if (u == null) break;
            else subList.add(u);
        }
        jo.put("code",0);
        jo.put("count", list.size());
        jo.put("msg", "成功");
        jo.put("data",subList);
        return jo;
    }


    @RequestMapping("updateRecord")
    @ResponseBody
    public Object updateRecord(Data data,String table){
        int i = dataService.updateData(data,table);
        JSONObject jo = new JSONObject();
        if(i > 0){
            jo.put("code",0);
            jo.put("message","修改成功");
        }
        return jo;
    }

    @RequestMapping("addRecord")
    @ResponseBody
    public Object addRecord(Data data,String table){
        int i = dataService.insertData(data,table);
        JSONObject jo = new JSONObject();
        if(i > 0){
            jo.put("code",0);
            jo.put("message","插入成功");
        }
        return jo;
    }
    @RequestMapping("delRecord")
    @ResponseBody
    public Object delRecord(Integer id,String table){
        int i = dataService.delDataById(id,table);
        JSONObject jo = new JSONObject();
        if(i > 0){
            jo.put("code",0);
            jo.put("message","删除成功");
        }else{
            jo.put("code",1);
            jo.put("message","删除失败");
        }
        return jo;
    }

}
