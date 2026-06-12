package com.nanxinda.exception_;

public class ThrowsDetail {
    public static void main(String[] args) {
        f2();

    }
    //运行异常，不进行运行异常不会编译报错
    public static void f2() {
        //1.对于编译异常，程序中必须处理，比如try-catch或者throws
        //2.对于运行时异常，程序中如果没有处理，默认就是throws的方式处理
        int n1 = 10;
        int n2 = 0;
        double res = n1/n2;
    }
}
class Father{
    public void method() throws RuntimeException{}
}
class Son extends Father{
    //3.子类重写父类的方法时，对抛出异常的规定：子类重写的方法
    //所抛出的异常类型要么和父类抛出的异常一致，要么为父类抛出异常类型的子类型
    @Override
    public void method() throws NullPointerException {}
}