package com.chen.pojo;


import java.sql.Timestamp;

/**
 * @Description
 * @auther 1
 * @create 2020-02-22 15:39
 */
public class User {
    private int id;
    private String name;
    private String pwd;
    private Timestamp time;

    public User() {
    }

    public User(int id, String name, String pwd, Timestamp time) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.time = time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", time=" + time +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
