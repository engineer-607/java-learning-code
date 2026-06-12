package com.nanxinda.final_;

public class Final02 {
    /*
    final细节：
    1）如果一个类已经时final类，就没有必要再将方法修饰成final方法。
    2）final不能修饰构造方法（即构造器）
    3）final和static往往搭配使用，效率更高，不会导致类加哉（底层编译器做了优化处理）
    4）包装类（Integer，Double，Float，Boolean都是final），String也是final类
     */
    public static void main(String[] args) {
        //有的情况只需要某个静态属性不需要整个类都被加载
        System.out.println(Demo.i);

    }
}
class Demo{
    public static final int i = 16;
    static {
        System.out.println("静态代码块");
    }
}