package com.chen.dao;

import com.chen.pojo.Blog;
import com.chen.utils.IDUtils;
import com.chen.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Blog> blogs = mapper.queryBlogIF(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }
        sqlSession.close();
    }
}
