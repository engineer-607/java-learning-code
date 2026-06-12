package com.nanxinda.annotaion;

public class Override_ {
    public static void main(String[] args) {

    }
}
class Father{
    public void fly(){
        System.out.println("Father fly...");
    }
}
/*
     1.@Override注解放在fly方法上，表示子类的fly方法重写了父类的fly
     2.起到校验的作用，编译器会检查是否真的构成重写
     3.如果没有构成重写，则编译报错
     4.Override源码：
        @interface表示Override是一个注解类
        @Target(ElementType.METHOD)
        @Retention(RetentionPolicy.SOURCE)
        public @interface Override {
        }
      5.@Override只能修饰方法（@Override源码中@Target(ElementType.METHOD)说明
      只能修饰方法），不能修饰其他类、包、属性等
      6.@Target是修饰注解的注解，称为元注解
 */
class Son extends Father{
    @Override
    public void fly() {
        System.out.println("Son fly...");
    }
}