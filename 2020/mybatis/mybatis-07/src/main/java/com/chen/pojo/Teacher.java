package com.chen.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author czb
 * @Description
 * @date 2020/3/13 10:56
 */
@Data
@ToString
public class Teacher {
    private int id;
    private String name;
    //一个老师拥有多个学生
    private List<Student> students;
}
