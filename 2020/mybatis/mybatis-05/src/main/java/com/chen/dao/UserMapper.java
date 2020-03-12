package com.chen.dao;

import com.chen.pojo.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Description
 * @auther 1
 * @create 2020-02-22 15:42
 */
public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();
}
