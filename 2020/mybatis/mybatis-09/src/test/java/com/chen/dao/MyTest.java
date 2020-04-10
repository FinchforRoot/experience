package com.chen.dao;

import com.chen.pojo.User;
import com.chen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @author czb
 * @Description
 * @date 2020/4/10 8:50
 */
public class MyTest {

    //从中可得优先使用一级缓存，第二次查询不会再与数据库进行交互，除非它进行了增删改，因为增删改会影响数据库数据的时效性
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
        System.out.println("-------------------------------");
        User user2 = mapper.getUserById(2);
        System.out.println(user2);
        System.out.println("-------------------------------");
        User user3 = mapper.getUserById(1);
        System.out.println(user3);
        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession2 = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper mapper2 = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        User user1 = mapper2.getUserById(1);
        System.out.println(user);
        System.out.println(user1);
        sqlSession.close();
        sqlSession2.close();
    }
}
