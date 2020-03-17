package com.chen.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author czb
 * @Description
 * @date 2020/3/17 15:41
 */
@Data
@ToString
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date createTime;    //属性名和字段名不一致
    private int views;
}
