package com.czvisual.controller;

import com.czvisual.entity.TreeNode;
import com.czvisual.entity.User;
import com.czvisual.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.*;

/*
 * 首页控制器
 * 包括根路径重定向，导航栏树的构建，用户基本信息的查询
 */

@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    @RequestMapping("/session")
    @ResponseBody
    public String session() {
        String tmp = "<pre>";
        Enumeration<String> e = session.getAttributeNames();
        while (e.hasMoreElements()) {
            String t = e.nextElement();
            tmp += t + " " + session.getAttribute(t);
            tmp += "<br>";
        }
        tmp += "<hr>";
        Session session2 = SecurityUtils.getSubject().getSession();
        Iterator<Object> it = session2.getAttributeKeys().iterator();
        while(it.hasNext()){
            String t = (String) it.next();
            tmp += t+session2.getAttribute(t)+"<br>";
        }

        return tmp;
    }

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("loginname", ((User) session.getAttribute("user")).getRealname());
        model.addAttribute("id", ((User) session.getAttribute("user")).getId());
        return "view/index";
    }

    @ResponseBody
    @RequestMapping("/initTreeNode")
    public String initTreeNode() {
        return null;
    }


}
