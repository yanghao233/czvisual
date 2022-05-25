package com.czvisual.service;

import com.czvisual.entity.User;
import com.czvisual.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    public User findUserByUserId(int id) {
        return userMapper.findUserByUserId(id);
    }

    public int checkUser(User user) {
        return userMapper.checkUser(user.getUsername());
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public List<User> findAll(User user) {
        return userMapper.findAll(user);
    }

    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    public int changePassword(String username, String password, String salt) {
        return userMapper.changePassword(username, password, salt);
    }

    public int deleteUser(int id) {
        return userMapper.deleteUser(id);
    }
}
