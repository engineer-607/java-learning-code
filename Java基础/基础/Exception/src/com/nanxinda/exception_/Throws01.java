package com.nanxinda.exception_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Throws01 {
    public static void main(String[] args) throws FileNotFoundException{
        //编译异常必须处理，否则会编译报错
        f1();

    }
    public static void f1() throws FileNotFoundException {
        /*
        这里的异常是一个FileNotFoundException编译异常
        处理方案：
        1）使用try-catch-finally
        2）使用throws，抛出异常，让调用f1方法的调用者（方法）处理
        throws后面的异常类型可以是方法中产生的异常类型，也可以是它的父类
        throws关键字后也可以是异常列表，即可以抛出多个异常
         */
        FileInputStream fileInputStream = new FileInputStream("D:\\Git");
    }
}
