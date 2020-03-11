package com.chen.dao;

import com.chen.pojo.User;
import com.chen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther 1
 * @create 2020-02-22 15:58
 */
public class UserMapperTest {

    @Test
    public void test() {
        //获取sqlSession
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        //方式一:执行sql
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(1);
        System.out.println(user);
        //关闭sqlSession
        sqlSession.close();
    }
}
