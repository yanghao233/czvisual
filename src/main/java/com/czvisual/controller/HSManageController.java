package com.czvisual.controller;

import com.alibaba.fastjson.JSONObject;
import com.czvisual.entity.Hotspring;
import com.czvisual.entity.User;
import com.czvisual.service.HotspringService;
import com.fasterxml.jackson.databind.JsonSerializable;
import jdk.security.jarsigner.JarSigner;
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
        model.addAttribute("area", hotspringService.findTypeByGroup("area"));
        model.addAttribute("commonType", hotspringService.findTypeByGroup("commonType"));
        model.addAttribute("heatDisplayType", hotspringService.findTypeByGroup("heatDisplayType"));
        model.addAttribute("heatDamageType", hotspringService.findTypeByGroup("heatDamageType"));
        model.addAttribute("chemicalCompositionType", hotspringService.findTypeByGroup("chemicalCompositionType"));
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

    @RequestMapping("addHotSpring")
    @ResponseBody
    public Object addHotSpring(Hotspring hotspring) {
        JSONObject jo = new JSONObject();
        Integer[] res = hotspringService.addHotSpring(hotspring);
        if (res[0] > 0 && res[1] == 0) {
            jo.put("code", 0);
            jo.put("message", "添加成功");
        } else {
            jo.put("code", 1);
            jo.put("message", "添加失败");
        }
        return jo;
    }

    @RequestMapping("delHotSpring")
    @ResponseBody
    public Object delHotSpring(String tableName) {
        JSONObject jo = new JSONObject();
        int[] res = hotspringService.delHotSpring(tableName);
        if (res[0] > 0 && res[1] == 0) {
            jo.put("code", 0);
            jo.put("message", "删除成功");
        } else {
            jo.put("code", 1);
            jo.put("message", "删除失败");
        }
        return jo;
    }

    @RequestMapping("updateHotSpring")
    @ResponseBody
    public Object updateHotSpring(Hotspring hotspring) {
        JSONObject jo = new JSONObject();
        int i = hotspringService.updateHotSpring(hotspring);
        if (i > 0) {
            jo.put("code", 0);
            jo.put("message", "更新成功");
        } else {
            jo.put("code", 1);
            jo.put("message", "更新失败");
        }
        return jo;
    }


}
