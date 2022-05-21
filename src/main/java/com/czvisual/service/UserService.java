package com.czvisual.service;

import com.czvisual.entity.User;
import com.czvisual.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }
}
