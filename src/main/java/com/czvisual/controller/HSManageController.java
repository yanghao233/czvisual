package com.czvisual.controller;

import com.alibaba.fastjson.JSONObject;
import com.czvisual.entity.Hotspring;
import com.czvisual.entity.User;
import com.czvisual.service.HotspringService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


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

    @RequestMapping("hs")
    public String hs(Model model) {
        if (((User) SecurityUtils.getSubject().getPrincipal()).getType() == 0) {
            model.addAttribute("edit", 1);
            model.addAttribute("del", 1);
        }
        model.addAttribute("area",hotspringService.findTypeByGroup("area"));
        model.addAttribute("commonType",hotspringService.findTypeByGroup("commonType"));
        model.addAttribute("heatDisplayType",hotspringService.findTypeByGroup("heatDisplayType"));
        model.addAttribute("heatDamageType",hotspringService.findTypeByGroup("heatDamageType"));
        model.addAttribute("chemicalCompositionType",hotspringService.findTypeByGroup("chemicalCompositionType"));
        return "/view/hsmanage/hsmanage.html";
    }

    @RequestMapping("getAllHotspring")
    @ResponseBody
    public Object getAllHotspring() {
        JSONObject jo = new JSONObject();
        jo.put("code", 0);
        List<Hotspring> hotspringList = hotspringService.findAll();
        jo.put("data", hotspringList);
        jo.put("count", hotspringList.size());
        return jo;
    }

    @RequestMapping("getFilteredAllHotspring")
    @ResponseBody
    public Object getFilteredAllHotspring(String area, String commonType, String heatDisplayType, String heatDamageType, String chemicalCompositionType, int page, int limit) {
        JSONObject jo = new JSONObject();
        jo.put("code", 0);
        List<Hotspring> hotspringList = hotspringService.findFilteredAll(area, commonType, heatDisplayType, heatDamageType, chemicalCompositionType);
        List<Hotspring> subList = new ArrayList<>(limit);
        for (int i = 0; i < limit && (page - 1) * limit + i < hotspringList.size(); i++) {
            Hotspring u = hotspringList.get((page - 1) * limit + i);
            if (u == null) break;
            else subList.add(u);
        }
        jo.put("data", subList);
        jo.put("count", hotspringList.size());
        return jo;
    }


}
