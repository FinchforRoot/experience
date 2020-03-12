package com.chen.dao;

import com.chen.pojo.User;
import com.chen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author czb
 * @Description
 * @date 2020/3/12 16:36
 */
public class UserMapperTest {
    @Test
    public void getUserTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUsers();
        for (User user : users){
            System.out.println(user);
        }
        sqlSession.close();
    }
}
