package com.chen.pojo;

import lombok.Data;

/**
 * @author czb
 * @Description
 * @date 2020/3/13 10:56
 */
@Data
public class Student {
    private int id;
    private String name;
    private Teacher teacher;
}
