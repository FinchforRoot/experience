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
        List<User> userList = userMapper.getUserList();

        //方式二:直接通过接口的方法名
        //List<User> userList = sqlSession.selectList("com.chen.dao.UserMapper.getUserList");

        for (User user : userList) {
            System.out.println(user);
        }
        //关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void getUserById() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void addUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int num = mapper.addUser(new User(3, "name4", "123321", Timestamp.valueOf("2019-5-4 00:00:00")));
        if (num >0){
            System.out.println("插入成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int num = mapper.updateUser(new User(3, "name", "1111111111111111", Timestamp.valueOf("2018-7-1 00:00:00")));
        if (num>0){
            System.out.println("修改成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deletUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int i = mapper.deleteUserById(3);
        if (i>0){
            System.out.println("删除成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void addUserByMap(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userid",5);
        map.put("username","username");
        map.put("password","password");
        map.put("time",Timestamp.valueOf("2011-11-11 00:00:00"));
        int i = mapper.addUserByMap(map);
        if (i>0){
            System.out.println("插入成功");
        }
        sqlSession.commit();
        sqlSession.close();
    }
}
