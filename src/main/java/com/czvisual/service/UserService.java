package com.czvisual.service;

import com.czvisual.entity.User;
import com.czvisual.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public int checkUser(User user) {
        return userMapper.checkUser(user.getUsername());
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }
}
