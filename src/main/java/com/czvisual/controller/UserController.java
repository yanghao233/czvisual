package com.czvisual.controller;

import com.czvisual.config.UserCredentialsMatcher;
import com.czvisual.entity.User;
import com.czvisual.service.SessionService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    HttpSession session;


    @RequestMapping("")
    public String defaultMapping() {
        return "redirect:/index";
    }

    @RequestMapping("toLogin")
    public String toLogin() {
        //测试时用，用于免登录
        return "redirect:/login?username=1&pwd=1";
        //return "view/login";
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
            user.setRealname("普通用户");
            user.setPosition("普通用户");
            user.setType(3);
            user.setAvailable(1);
            int i = userService.addUser(user);
            if (i > 0) {
                return "注册成功";
            } else {
                return "注册失败";
            }
        }
    }


    @RequestMapping(value = "/user/isNameExist", method = POST)
    @ResponseBody
    public String isNameExist(User user) {
        int i1 = userService.checkUser(user);
        if (i1 == 1) {
            return "true";
        } else {
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
        model.addAttribute("user", SecurityUtils.getSubject().getPrincipal());
        return "view/user/updateProfile.html";
    }

    @RequestMapping("/user/changePassword")
    @ResponseBody
    public String changePassword(@RequestParam(required = true) int id, @RequestParam(required = true) String pwd, @RequestParam(required = true, defaultValue = "123456") String pwd1) {
        User currentLoginUser = (User) SecurityUtils.getSubject().getPrincipal();
        Subject subject = SecurityUtils.getSubject();
        User operateUser = null;
        if (subject.isPermitted("user:manageUser")) {
            operateUser = userService.findUserByUserId(id);
        } else if (subject.isPermitted("user:updateProfile")) {
            operateUser = currentLoginUser;
        } else {
            //没有权限修改
            return "2";
        }
        boolean isOldPwdRight = UserCredentialsMatcher.encryptPassword("md5", pwd, operateUser.getSalt(), 2).equals(operateUser.getPassword());
        if (isOldPwdRight) {
            //重新生成随机Salt
            String salt = UserCredentialsMatcher.generateSalt(6);
            //MD5加密迭代两次
            String newPassword = UserCredentialsMatcher.encryptPassword("md5", pwd1, salt, 2);
            int i = userService.changePassword(operateUser.getId(), newPassword, salt);
            if (i > 0) {
                return "1";
            } else {
                //异常导致更新密码失败
                return "0";
            }
        } else {
            //旧密码输入错误
            return "3";
        }

    }

    @RequestMapping("/user/updateUser")
    @ResponseBody
    public String updateUser(User user) {
        Subject subject = SecurityUtils.getSubject();
        User operateUser = (User) subject.getPrincipal();

        if(subject.isPermitted("user:manageUser") && operateUser.getId() != user.getId()){
            operateUser = userService.findUserByUserId(user.getId());
            operateUser.setPosition(user.getPosition());
            operateUser.setType(user.getType());
            operateUser.setAvailable(user.getAvailable());
            operateUser.setUsername(user.getUsername());
        }

        if(subject.isPermitted("user:updateProfile")){
            operateUser.setRealname(user.getRealname());
            operateUser.setSex(user.getSex());
            operateUser.setPhone(user.getPhone());
        }

        //数据更新到数据库
        int i = userService.updateUser(operateUser);
        if (i > 0) {
            //修改当前登录用户的个人信息
            if(operateUser.getId() == ((User)subject.getPrincipal()).getId()){
                session.setAttribute("user", operateUser);
            }else{
                //管理员修改其他用户的登录信息
                SessionService.stopSessionByUserid(operateUser.getId());
            }
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
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
