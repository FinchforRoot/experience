package com.chen.dao;

import com.chen.pojo.User;
import com.chen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * @Description
 * @auther 1
 * @create 2020-02-22 15:58
 */
public class UserMapperTest {

    static Logger logger = Logger.getLogger(UserMapperTest.class);

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

    @Test
    public void log4jTest(){
        logger.info("这是logger info");
        logger.error("这是logger error");
        logger.warn("这是logger warn");
    }
}
