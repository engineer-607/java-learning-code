package com.nanxinda.exception_;

public class Exception01 {
    /*
    异常处理机制
    当程序员认为一端代码可能出现异常/问题，可以使用try-catch异常处理机制来解决,
    ，即便出现了问题，也可以继续执行，从而保证程序的健壮性。
    将代码块->选中->快捷键ctrl+alt+t->选中try - catch
    异常：
    在程序执行过程发生的不正常情况称为“异常”（开发过程的语法错误和逻辑错误不是异常）
    异常事件分为两大类
    1）Error(错误）：Java虚拟机无法解决的严重问题（例如：JVM系统内部错误，资源耗尽）
    2）Exception：因编译错误或偶然的外在因素导致的一般性问题，可以使用针对性的代码进行处理
    （Exception分为两大类：运行时异常和编译时异常）

 * Throwable下的异常继承关系图（包含编译异常）
 *
 * Throwable (所有错误/异常的基类)
 *   │
 *   ├── Error (严重错误，程序无法处理)
 *   │    │
 *   │    ├── StackOverflowError (栈溢出错误)
 *   │    │
 *   │    └── OutOfMemoryError (内存溢出错误)
 *   │
 *   └── Exception (所有异常的父类)
 *        │
 *        ├── RuntimeException (运行时异常，非受检异常/非编译异常)
 *        │    │
 *        │    ├── NullPointerException (空指针异常)
 *        │    │
 *        │    ├── ArithmeticException (算术异常，如除以零)
 *        │    │
 *        │    ├── ArrayIndexOutOfBoundsException (数组越界异常)
 *        │    │
 *        │    ├── ClassCastException (类转换异常)
 *        │    │
 *        │    └── NumberFormatException (数字格式异常)
 *        │
 *        └── 编译异常 (Checked Exceptions，必须处理或声明)
 *             │
 *             ├── IOException (IO异常)
 *             │    │
 *             │    └── FileNotFoundException (文件未找到异常)
 *             │
 *             ├── SQLException (数据库异常)
 *             │
 *             ├── ClassNotFoundException (类找不到异常)
 *             │
 *             ├── ParseException (解析异常，如日期解析)
 *             │
 *             ├── InterruptedException (线程中断异常)
 *             │
 *             └── NoSuchMethodException (方法不存在异常)
     */
    //案例
    public static void main(String[] args) {
        //算术异常
        int num1=20;
        int num2 =0;
        try {
            int res = num1/num2;
        } catch (java.lang.Exception e) {
            System.out.println(e.getMessage());

        }
        System.out.println("程序继续执行");
        //数字格式异常
        String name="hsp";
        try {
            int num = Integer.parseInt(name);
            System.out.println(num);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        //类转化异常
        A a = new B();
        try {
            C c = (C)a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
class A {}
class B extends A{}
class C extends A{}