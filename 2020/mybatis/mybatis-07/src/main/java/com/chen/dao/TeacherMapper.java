package com.chen.dao;

import com.chen.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author czb
 * @Description
 * @date 2020/3/13 11:00
 */
public interface TeacherMapper {

    //获取老师
    List<Teacher> getTeacher();

    //获取指定老师下的所有学生及老师信息
    Teacher getTeachers(@Param("tid")int id);

    //获取指定老师下的所有学生及老师信息
    Teacher getTeachers2(@Param("tid")int id);

}
