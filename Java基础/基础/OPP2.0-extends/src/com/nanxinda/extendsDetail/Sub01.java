package com.nanxinda.extendsDetail;

public class Sub01 extends Base01{
    public Sub01(String name,int age){
        super(name,age);
        //父类的无参构造器被覆盖，子类的构造器必须使用super指明使用哪个构造器完成对父类的初始化
        //即给调用的父类构造器传参数
        System.out.println("子类构造器Sub01(String name,int age)被调用");
    }
}
