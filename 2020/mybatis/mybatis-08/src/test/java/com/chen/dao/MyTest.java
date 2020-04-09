package com.chen.dao;

import com.chen.pojo.Blog;
import com.chen.utils.IDUtils;
import com.chen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

/**
 * @author czb
 * @Description
 * @date 2020/3/17 15:40
 */
public class MyTest {
    @Test
    public void test1() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(IDUtils.getId());
        blog.setAuthor("李四");
        blog.setTitle("标题1");
        blog.setCreateTime(new Date());
        blog.setViews(100);
        mapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("标题3");
        mapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("标题4");
        mapper.addBlog(blog);

        blog.setId(IDUtils.getId());
        blog.setTitle("标题5");
        mapper.addBlog(blog);

        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        map.put("title","标题1");
        map.put("author","王五");
        List<Blog> blogs = mapper.queryBlogIF(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void test3(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
//        map.put("title","标题1");
        map.put("author","王五");
        map.put("views",9999);
        List<Blog> blogs = mapper.queryBlogChoose(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }

    @Test
    public void test4(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Map map = new HashMap();
        map.put("title","标题1");
        map.put("author","王五");
        map.put("id","2265ae05881c43688b724d635e100917");
        mapper.updateBlog(map);
        sqlSession.close();
    }
}
