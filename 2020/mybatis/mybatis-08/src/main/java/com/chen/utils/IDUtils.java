package com.chen.utils;

import org.junit.Test;

import java.util.UUID;

/**
 * @author czb
 * @Description
 * @date 2020/3/17 15:54
 */
public class IDUtils {
    public static String getId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    @Test
    public void test(){
        System.out.println(IDUtils.getId());
    }
}
