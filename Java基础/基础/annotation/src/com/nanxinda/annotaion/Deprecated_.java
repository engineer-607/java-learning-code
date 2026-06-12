package com.nanxinda.annotaion;

public class Deprecated_ {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.n1);

    }
}
/*
注解@Deprecated
1.修饰某个元素（方法、类、字段、包、参数），表示该元素已经过时
2，即不再推荐使用，但是仍然可以使用
3.源码：
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Deprecated {
}
4.作用：@Deprecated可以做到新旧版本的兼容和过渡

 */
@Deprecated
class A{
    @Deprecated
    public int n1 = 10;
    @Deprecated
    public void hi(){

    }
}