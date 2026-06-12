package com.nanxinda.codeblock;

public class CodeBlockDetail02 {
    /*
    代码块使用细节2.0：
    创建一个对象时，在一个类中调用顺序是（重点，难点）：
    1）调用静态代码块和静态属性初始化（注意：静态代码块和
    静态属性初始化调用的优先级一样，如果有多个静态代码块和
    多个静态变量初始化，则按他们定义的顺序调用）
    2）调用普通代码块和普通属性的初始化（注意：普通代码块和普通属性初始化调用的优先级一样
    如果有多个普通代码块和多个普通属性初始化，则按定义顺序调用）
    3）构造器被调用
    4）构造器最前面除了隐含super()和调用普通代码块
    5）静态代码块只能调用静态成员，普通代码块可以调用任意成员
     */
    public static void main(String[] args) {
        A a = new A();
    }
}
class A{
    public A(){
        System.out.println("构造器被调用");
    }
    public int n2 = getN2();
    {
        System.out.println("普通代码快被调用");
    }
    public static int n1 =getVal();
    static {
        System.out.println("静态代码块被调用");
    }
    public static int getVal(){
        System.out.println("getN1被调用");
        return  100;
    }
    public int getN2(){
        System.out.println("getN2被调用");
        return 200;
    }
}