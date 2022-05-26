package com.czvisual;


import com.czvisual.controller.UserController;
import com.czvisual.entity.User;
import com.czvisual.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserController userController;

    @Test
    public void findAll() {
        User user = new User();
        user.setRealname("刘");
        List<User> allUsers = userService.findAll(user);
        for (User u : allUsers) {
            System.out.println(u.toString());
        }
    }

    @Test
    public void findUserByUsername() {
        String username = "1";
        System.out.println(userService.findUserByUsername(username));
    }

/*    @Test
    public void changePassword() {
        System.out.println(userController.changePassword(2, "123456"));
    }*/

    @Test
    public void updateUser() {
        User user = new User();
        user.setUsername("test");
        user.setRealname("test");
        user.setPosition("普通用户");
        user.setSex(1);
        user.setAvailable(0);
        user.setPhone("1");
        user.setId(15);
        user.setType(3);
        System.out.println(userController.updateUser(user));
    }

    /*@Test
    public void deleteUser() {
        System.out.println(userController.deleteUser(20));
    }
}
