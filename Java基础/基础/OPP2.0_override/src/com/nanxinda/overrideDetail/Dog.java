package com.nanxinda.overrideDetail;

public class Dog extends Animal{
    /*
    1）Dog是Animal的子类
    2）Dog的cry方法和Animal的cry定义形式一样（名称，返回类型，参数）
    称Dog的cry方法重写Animal的cry方法
     */
    public void cry(){
        System.out.println("小狗汪汪叫...");
    }
    /*
    重写的细节：
    1）子类的返回类型和父类方法返回类型一样或者是父类返回类型的子类
    eg.父类返回Object，子类返回String
     */
    public String m1(){
        return null;
    }
    //2）子类方法不能缩小父类的访问权限(public > protected > 默认 >private
    public void sayOK(){}

}
