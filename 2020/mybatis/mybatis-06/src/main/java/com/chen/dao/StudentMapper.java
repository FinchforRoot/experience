package com.chen.dao;

import com.chen.pojo.Student;

import java.util.List;

/**
 * @author czb
 * @Description
 * @date 2020/3/13 11:00
 */
public interface StudentMapper {
    //查询所有的学生，以及对应的老师的信息
    List<Student> getStudent();
    List<Student> getStudent2();
}
