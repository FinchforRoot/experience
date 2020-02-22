package com.chen.dao;

import com.chen.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther 1
 * @create 2020-02-22 15:42
 */
public interface UserMapper {
    List<User> getUserList();
    List<User> getUserById(int id);
    int addUser(User user);
    int updateUser(User user);
    int deleteUserById(int id);
    int addUserByMap(Map<String, Object> map);
}
