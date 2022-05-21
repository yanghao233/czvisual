package com.czvisual;

import com.czvisual.config.UserCredentialsMatcher;
import com.czvisual.entity.User;
import com.czvisual.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CzvisualApplicationTests {
    @Autowired
    private UserService service;

    @Test
    public void loginTest() {
        Subject subject = SecurityUtils.getSubject();
        //第二步：封装token  凭证
        UsernamePasswordToken token = new UsernamePasswordToken("a", "123456");
        try {
            subject.login(token);
            User users = service.findUserByUsername("a");

        } catch (UnknownAccountException e) {
            System.out.println("不存在这样的用户");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        }
    }
}
