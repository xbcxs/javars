package com.xbcxs.common.exception;

/**
 * 1.若一段代码前throw new Exception()直接代码上抛出异常，并且这个异常没有被捕获，这段代码将产生编译时错误。
 * 2.若一段代码前有异常抛出，并且这个异常被try...catch所捕获，若此时catch语句中没有抛出新的异常，则这段代码能够被执行。
 * @author xiaosh
 * @date 2020/4/28
 */
public class ExceptionTest {

    public void test1() throws Exception  {
        throw new Exception("模拟Exception");
        // System.out.println("异常后"); // 编译错误，「无法访问的语句」

    }

    public void test2() throws Exception  {
        throw new RuntimeException("模拟RuntimeException");
        // System.out.println("异常后"); // 编译错误，「无法访问的语句」
    }

    public void test3() {
        int a = 1, b = 0;
        a = a / b; // 分母为零，运行时异常
        System.out.println("异常后"); //抛出异常，不会执行
    }

    public void test4() {
        try {
            // 异常代码
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("异常后"); // 可以执行
    }

    public void test5(int a ) {
        if(a > 0) {
            throw new RuntimeException("参数越界");
        }
        System.out.println("异常后"); // 编译通过
    }

    public void test6(int a) throws Exception {
        if(a > 0) {
            throw new Exception("参数越界");
        }
        System.out.println("异常后"); // 编译通过
    }

}
