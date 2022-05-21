package com.czvisual.mapper;

import com.czvisual.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findUserById(int id);
    User findUserByUsername(String username);
}
