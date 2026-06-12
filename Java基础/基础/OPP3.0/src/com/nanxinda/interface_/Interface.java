package com.nanxinda.interface_;

public class Interface {
    /*
    接口：
    给出一些没有实现的方法，封装到一起，到某个类要使用的时候，在跟据具体情况把这些方法写出来
    语法：interface 接口名{}
    class implements 接口{
         自己属性;
         自己方法;
         必须实现的接口的抽象方法
    }
    接口使用细节：
    1）接口不能被实例化
    2）接口中所有方法是public方法，接口中抽象方法，可以不用abstract修饰
    3）一个普通类实现接口，就必须将该接口的所有方法都实现（alt+enter）
    4）抽象类实现接口，可以不用实现接口的方法
    5）一个类同时可以实现多个接口
    6）接口中的属性只能是final，而且是public static final修饰
    public static final int n1 = 1;(必须初始化)
    7）接口中属性的访问形式：接口名.属性名
    8）接口不能继承其他的类，但是可以继承多个别的接口
    9）接口的修饰符只能是public

     */
    static void main() {
        System.out.println(A.n1);
    }
}
interface A{
    int n1 = 10;
    void say();
}
abstract class B implements A{}

interface C{
    void eat();
}
interface E extends A,C{}

class D implements A,C{

    @Override
    public void say() {

    }

    @Override
    public void eat() {

    }
}
