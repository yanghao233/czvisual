package com.czvisual.controller;

import com.czvisual.config.UserCredentialsMatcher;
import com.czvisual.entity.User;
import com.czvisual.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin() {
        return "view/login";
    }

    @RequestMapping("toRegister")
    public String register() {
        return "view/register";
    }

    //登陆   shiro登陆
    @RequestMapping("login")
    public String login(Model model, HttpServletRequest request, String username, String pwd) {


        //第一步：建立subject
        Subject subject = SecurityUtils.getSubject();
        //第二步：封装token  凭证
        UsernamePasswordToken token = new UsernamePasswordToken(username, pwd);
        //第三步：登陆
        try {
            //只要能通过认证就能通过了
            subject.login(token);
            User users = userService.findUserByUsername(username);
            //把user放进session
            request.getSession().setAttribute("user", users);
            //model.addAttribute("loginname", users.getRealname());
            //model.addAttribute("id", users.getId());
            //把yonghu放进session
            request.getSession().setAttribute("yonghu", users.getRealname());
            return "redirect:/index/";//跳转首页
        } catch (UnknownAccountException e) {
            //用户为空或不存在
            model.addAttribute("msg", "不存在这样的用户!");
            return "view/login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码输入不对!");
            return "view/login";
        }
    }


    @RequestMapping("register")
    @ResponseBody
    public Object addUser(User user){
        int i1 = userService.checkUser(user);
        if(i1==1){
            return "当前登陆名已存在";
        }else {
            //加盐
            String salt = UserCredentialsMatcher.generateSalt(6);
            //MD5加密迭代两次
            user.setPassword(UserCredentialsMatcher.encryptPassword("md5", "123456", salt, 2));
            user.setType(3);
            user.setAvailable(1);
            user.setSalt(salt);
            System.out.println(salt);
            int i = userService.addUser(user);
            if (i > 0) {
                return "注册成功";
            } else {
                return "注册失败";
            }
        }
    }
}
