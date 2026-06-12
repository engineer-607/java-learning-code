package com.nanxinda.test;

public class B {
    public void say(){
        A a = new A();
        System.out.println("A和B在同一个包（com.nanxinda.test）下 n1="+a.n1+" n2"+a.n2+" n3="+a.n3);
        System.out.println("在同一包下不同类中，可以访问方法的修饰符：");
        a.m1();
        a.m2();
        a.m3();
    }


}
