package com.nanxinda.annotaion;

public class Annotation {
    /*
    注解
    1）注解，用于修饰解释包、类、方法、属性、构造器、局部变量等数据信息
    2）和注释一样，注解不影响程序逻辑，但注解可以被编译或运行，相当于嵌入在代码中的补充信息
    三个基本的Annotation：
    1）@Override：限定某个方法，是重写父类方法，该注解只能用于方法
    2）@Deprecated：用于表示某个程序元素（类、方法等）已过时
    3）@SuppressWarnings：抑制编译器警告
    4）Override源码：
    @interface表示Override是一个注解类
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface Override {
    }
     */
    /*
    元注解常见种类（修饰其他注解的注解）：
    1）Retention//指定注解的作用范围 三种SOURCE、CLASS、RUNTIME
    2）Target//指定注解可以在哪些地方使用
    3）Documented //指定该注解是都会在javadoc体现
    4）Inherited //子类会继承父类注解
     */
}
