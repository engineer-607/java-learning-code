package com.nanxinda.Object.Finalize;

public class Finalize {
    /*
    finalize方法
    1）当对象被回收时，系统自动调用该对象的finalize方法，子类可以重写该方法
    做一些释放资源的操作
     */
    public static void main(String[] args) {
        Car car = new Car("宝马");
        car=null;
        /*
        car对象就是一个垃圾，垃圾回收器就会去回收（销毁）对象，在销毁对象前，会调用
        该对象的finalize方法；程序员可以在finalize中，编写自己的业务逻辑代码（比如
        释放资源：数据库连接，或者打开文件...）；如果程序员不重写finalize，就会调用Object类的
        finalize，即默认处理；如果程序员重写finalize，就可以实现自己的逻辑
         */
        System.gc();
        System.out.println("程序退出");
        //垃圾回收机制调用由系统来决定（即有GC算法），并非有垃圾就回去回收
    }
}
class Car{
    private String name;

    public Car(String name) {
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("我们销毁"+name);
        System.out.println("释放某些资源");

    }
}
