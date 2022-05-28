package com.czvisual.controller;

import com.alibaba.fastjson.JSONObject;
import com.czvisual.config.UserCredentialsMatcher;
import com.czvisual.entity.User;
import com.czvisual.service.SessionService;
import com.czvisual.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.RedirectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;
    @Autowired
    HttpSession session;

    @RequestMapping("")
    public String defaultMapping() {
        return "redirect:/index";
    }

    @RequestMapping("toLogin")
    public String toLogin() {
        //测试时用，用于免登录
        //return "redirect:/login?username=1&pwd=1";
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


    @RequestMapping({"register"})
    public Object register(User user, Model model) {
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
            user.setSex(2);
            user.setAvailable(1);
            int i = userService.addUser(user);
            if (i > 0) {
                model.addAttribute("msg", "注册成功!");
                return "view/login";
            } else {
                return "注册失败";
            }
        }
    }

    @RequestMapping({"/user/addUser"})
    @ResponseBody
    public Object addUser(User user) {
        JSONObject jo = new JSONObject();
        int i1 = userService.checkUser(user);
        if (i1 == 1) {
            jo.put("code", 1);
            jo.put("message", "当前登陆名已存在");
            return jo;
        } else {
            //加盐
            String salt = UserCredentialsMatcher.generateSalt(6);
            //MD5加密迭代两次
            user.setPassword(UserCredentialsMatcher.encryptPassword("md5", "123456", salt, 2));
            user.setSalt(salt);
            user.setAvailable(user.getAvailable() == null ? 0 : 1);
            switch (user.getType()) {
                case 0:
                    user.setPosition("系统管理员");
                    break;
                case 1:
                    user.setPosition("数据录入员");
                    break;
                case 2:
                    user.setPosition("数据分析员");
                    break;
                case 3:
                    user.setPosition("普通用户");
                    break;

            }
            int i = userService.addUser(user);
            if (i > 0) {
                jo.put("code", 0);
                jo.put("message", "添加成功,初始密码123456");
            } else {
                jo.put("code", 2);
                jo.put("message", "数据插入异常");
            }
            return jo;
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
        return "view/toLogin";
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
    public Object changePassword(@RequestParam(required = true) int id, String pwd, String pwd1) {
        JSONObject jo = new JSONObject();
        User currentLoginUser = (User) SecurityUtils.getSubject().getPrincipal();
        Subject subject = SecurityUtils.getSubject();
        User operateUser = null;
        if (subject.isPermitted("user:manageUser")) {
            operateUser = userService.findUserByUserId(id);
        } else if (subject.isPermitted("user:updateProfile")) {
            operateUser = currentLoginUser;
        } else {
            jo.put("code", 2);
            jo.put("message", "没有权限修改");
            return jo;
        }

        boolean isOldPwdRight = false;
        if (pwd != null) {
            isOldPwdRight = UserCredentialsMatcher.encryptPassword("md5", pwd, operateUser.getSalt(), 2).equals(operateUser.getPassword());
        }
        if (pwd == null || pwd1 == null) {
            if (subject.isPermitted("user:manageUser")) {
                isOldPwdRight = true;
                pwd1 = "123456";
            }
        }

        if (isOldPwdRight) {
            //重新生成随机Salt
            String salt = UserCredentialsMatcher.generateSalt(6);
            //MD5加密迭代两次
            String newPassword = UserCredentialsMatcher.encryptPassword("md5", pwd1, salt, 2);
            int i = userService.changePassword(operateUser.getId(), newPassword, salt);
            if (i > 0) {
                jo.put("code", 1);
                jo.put("message", "修改成功");
                if(currentLoginUser.getId() == operateUser.getId()){
                    subject.logout();
                }
                sessionService.stopSessionByUserid(id);
                return jo;
            } else {
                jo.put("code", 0);
                jo.put("message", "异常导致更新密码失败");
                return jo;
            }
        } else {
            jo.put("code", 3);
            jo.put("message", "旧密码输入错误");
            return jo;
        }
    }

    @RequestMapping("/user/updateUser")
    @ResponseBody
    public JSONObject updateUser(User user) {
        Subject subject = SecurityUtils.getSubject();
        User operateUser = (User) subject.getPrincipal();
        JSONObject jo = new JSONObject();
        if (subject.isPermitted("user:manageUser") && operateUser.getId() != user.getId()) {
            operateUser = userService.findUserByUserId(user.getId());
            switch (user.getType()) {
                case 0:
                    operateUser.setPosition("系统管理员");
                    break;
                case 1:
                    operateUser.setPosition("数据录入员");
                    break;
                case 2:
                    operateUser.setPosition("数据分析员");
                    break;
                case 3:
                    operateUser.setPosition("普通用户");
                    break;

            }
            operateUser.setType(user.getType());
            operateUser.setAvailable(user.getAvailable() == null ? 0 : 1);
            operateUser.setUsername(user.getUsername());
        }

        if (subject.isPermitted("user:updateProfile")) {
            operateUser.setRealname(user.getRealname());
            operateUser.setSex(user.getSex());
            operateUser.setPhone(user.getPhone());
        }

        //数据更新到数据库
        int i = userService.updateUser(operateUser);
        if (i > 0) {
            //修改当前登录用户的个人信息
            if (operateUser.getId() == ((User) subject.getPrincipal()).getId()) {
                session.setAttribute("user", operateUser);
            } else {
                //管理员修改其他用户的登录信息
                sessionService.stopSessionByUserid(operateUser.getId());
            }
            jo.put("code", 0);
            jo.put("message", "修改成功");
        } else {
            jo.put("code", 1);
            jo.put("message", "修改失败");
        }
        return jo;
    }

    @RequestMapping("/user/selectAllUser")
    @ResponseBody
    public Map<String, Object> selectAllUser(@RequestParam(required = false) String username,@RequestParam(required = false) String realname, @RequestParam Integer page, @RequestParam Integer limit) {
        User user = new User();
        user.setUsername(username);
        user.setRealname(realname);
        List<User> users = userService.findAll(user);
        List<User> subUsers = new ArrayList<>(limit);
        for (int i = 0; i < limit && (page - 1) * limit + i < users.size(); i++) {
            User u = users.get((page - 1) * limit + i);
            if (u == null) break;
            else subUsers.add(u);
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("count", users.size());
        map.put("msg", "");
        map.put("data", subUsers);
        return map;
    }

    @RequestMapping("/user/deleteUser")
    @ResponseBody
    //public String deleteUser(@RequestBody JSONObject jsonObject) {
    public JSONObject deleteUser(int id) {
        JSONObject jo = new JSONObject();
        if (id == ((User) SecurityUtils.getSubject().getPrincipal()).getId()) {
            jo.put("code", 2);
            jo.put("message", "不能删除自己");
            return jo;
        }
        int i = userService.deleteUser(id);
        if (i > 0) {
            jo.put("code", 0);
            jo.put("message", "删除成功");
            return jo;
        } else {
            jo.put("code", 1);
            jo.put("message", "删除错误");
            return jo;
        }
    }
}
