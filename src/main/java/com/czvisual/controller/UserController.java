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
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("")
    public String defaultMapping() {
        return "redirect:/index";
    }

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
            return "redirect:/index";//跳转首页
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
    public Object addUser(User user) {
        int i1 = userService.checkUser(user);
        if (i1 == 1) {
            return "当前登陆名已存在";
        } else {
            //加盐
            String salt = UserCredentialsMatcher.generateSalt(6);
            //MD5加密迭代两次
            user.setPassword(UserCredentialsMatcher.encryptPassword("md5", user.getPassword(), salt, 2));
            user.setSalt(salt);
            int i = userService.addUser(user);
            if (i > 0) {
                return "注册成功";
            } else {
                return "注册失败";
            }
        }
    }


    @RequestMapping(value = "/user/isNameExist",method = POST)
    @ResponseBody
    public String isNameExist(User user){
        int i1 = userService.checkUser(user);
        if (i1 == 1) {
            return "true";
        }else{
            return "false";
        }
    }

    @RequestMapping("logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "view/login";
    }

    @RequestMapping("/user/manageUser")
    public String manageUser(Model model) {
        return "view/user/manageUser.html";
    }

    @RequestMapping("/user/updateProfile")
    public String updateProfile(Model model) {
        return "view/user/updateProfile.html";
    }

    @RequestMapping("/user/changePassword")
    @ResponseBody
    public String changePassword(@DefaultValue("123456") String password) {
        String salt = UserCredentialsMatcher.generateSalt(6);
        //MD5加密迭代两次
        String newPassword = UserCredentialsMatcher.encryptPassword("md5", password, salt, 2);
        int i = userService.changePassword(newPassword, salt);
        if (i > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping("/user/updateUser")
    @ResponseBody
    public String updateUser(User user) {
        int i = userService.updateUser(user);
        if (i > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping("/user/deleteUser")
    @ResponseBody
    public String deleteUser(int id) {
        int i = userService.deleteUser(id);
        if (i > 0) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }
}
