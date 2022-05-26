package com.czvisual.controller;

import com.czvisual.entity.User;
import com.czvisual.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        //String tmp = "<pre>";
        //Enumeration<String> e = session.getAttributeNames();
        //while (e.hasMoreElements()) {
        //    String t = e.nextElement();
        //    tmp += t + " " + session.getAttribute(t);
        //    tmp += "<br>";
        //}
        //tmp += "<hr>";
        //Session session2 = SecurityUtils.getSubject().getSession();
        //Iterator<Object> it = session2.getAttributeKeys().iterator();
        //while(it.hasNext()){
        //    String t = (String) it.next();
        //    tmp += t+session2.getAttribute(t)+"<br>";
        //}
        Collection<Session> actsessions = ((DefaultWebSessionManager)((DefaultWebSecurityManager)SecurityUtils.getSecurityManager()).getSessionManager()).getSessionDAO().getActiveSessions();
        Iterator<Session> it = actsessions.iterator();
        while (it.hasNext()){
            Session ss = it.next();
            ((User)ss.getAttribute("user")).getId();
            ss.stop();
        }
        return null;
    }

    @RequestMapping("")
    public String index(Model model) {
        model.addAttribute("loginname", ((User) session.getAttribute("user")).getRealname());
        model.addAttribute("id", ((User) session.getAttribute("user")).getId());
        return "view/index";
    }

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("loginname", ((User) session.getAttribute("user")).getRealname());
        model.addAttribute("id", ((User) session.getAttribute("user")).getId());
        return "view/index/main";
    }

    @ResponseBody
    @RequestMapping("/initTreeNode")
    public String initTreeNode() {
        //测试临时用
        return "[{\"id\":2,\"title\":\"温泉管理\",\"icon\":\"\uE653\",\"href\":\"\",\"spread\":false,\"target\":null,\"children\":[{\"id\":6,\"title\":\"温泉总览\",\"icon\":\"\uE770\",\"href\":\"/hsManage/overview\",\"spread\":false,\"target\":null,\"children\":[],\"checkArr\":\"0\",\"parentId\":2},{\"id\":7,\"title\":\"温泉数据\",\"icon\":\"\uE657\",\"href\":\"/hsManage/tableview\",\"spread\":false,\"target\":null,\"children\":[],\"checkArr\":\"0\",\"parentId\":2}],\"checkArr\":\"0\",\"parentId\":1},{\"id\":3,\"title\":\"数据管理\",\"icon\":\"\uE663\",\"href\":\"\",\"spread\":false,\"target\":null,\"children\":[{\"id\":8,\"title\":\"温泉管理\",\"icon\":\"\uE65B\",\"href\":\"/dataManage/hs\",\"spread\":false,\"target\":null,\"children\":[],\"checkArr\":\"0\",\"parentId\":3},{\"id\":9,\"title\":\"数据操作\",\"icon\":\"\uE6B2\",\"href\":\"/dataManage/operatehsdata\",\"spread\":false,\"target\":null,\"children\":[],\"checkArr\":\"0\",\"parentId\":3}],\"checkArr\":\"0\",\"parentId\":1},{\"id\":4,\"title\":\"图表分析\",\"icon\":\"\uE716\",\"href\":\"/chartAnalysis/chart\",\"spread\":false,\"target\":\"\",\"children\":[],\"checkArr\":\"0\",\"parentId\":1},{\"id\":5,\"title\":\"用户管理\",\"icon\":\"\uE629\",\"href\":\"/user/manageUser\",\"spread\":false,\"target\":null,\"children\":[],\"checkArr\":\"0\",\"parentId\":1}]";
    }


}
