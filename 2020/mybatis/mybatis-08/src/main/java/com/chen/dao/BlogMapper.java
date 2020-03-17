package com.chen.dao;

import com.chen.pojo.Blog;

import java.util.List;
import java.util.Map;

/**
 * @author czb
 * @Description
 * @date 2020/3/17 15:48
 */
public interface BlogMapper {

    //插入数据
    int addBlog(Blog blog);

    //根据if条件判断查询数据
    List<Blog> queryBlogIF(Map map);
}
