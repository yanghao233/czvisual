package com.czvisual.mapper;

import com.czvisual.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findUserByUsername(String username);

    User findUserByUserId(int id);

    int checkUser(String username);

    int addUser(User user);

    List<User> findAll(User user);

    int updateUser(User user);

    int changePassword(Integer id, String password, String salt);

    int deleteUser(int id);

}
