package com.czvisual.controller;

import com.czvisual.entity.TreeNode;
import com.czvisual.entity.User;
import com.czvisual.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/*
 * 首页Ajax查询控制器
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
        return tmp;
    }

    @RequestMapping("*")
    public String index(Model model) {
        model.addAttribute("loginname", ((User) session.getAttribute("user")).getRealname());
        model.addAttribute("id", ((User) session.getAttribute("user")).getId());
        return "view/index";
    }

    @RequestMapping("/initTreeNode")
    public List<TreeNode> initTreeNode() {
        return new ArrayList<TreeNode>();
    }


}
