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
    private String password;
    private Timestamp time;

    public User() {
    }

    public User(int id, String name, String password, Timestamp time) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.time = time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
