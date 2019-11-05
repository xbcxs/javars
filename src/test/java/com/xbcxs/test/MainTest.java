package com.xbcxs.test;

import java.util.Properties;

/**
 * @author xiaosh
 * @date 2019/9/18
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
//        AbcExtender abcExtender = (AbcExtender) ExtendFactory.getExtendInstance(AbcExtender.class);
//        abcExtender.method1();

        Properties initProp = new Properties(System.getProperties());
        System.out.println("当前系统编码:" + initProp.getProperty("file.encoding"));
        System.out.println("当前系统语言:" + initProp.getProperty("user.language"));
        System.out.println(System.getProperty("file.encoding") );

        String charset = "UTF-8";//假定编码格式
        String str = "中文";
        boolean flag = str.equals(new String(str.getBytes(),charset));
        System.out.println(flag);
    }
}
