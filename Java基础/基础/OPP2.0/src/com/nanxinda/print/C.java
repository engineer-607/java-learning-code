package com.nanxinda.print;

import com.nanxinda.test.A;

public class C {
    public void setA() {
        A a = new A();
        System.out.println("在不同包下 n1="+a.n1);
        System.out.println("在不同包下，可以访问方法的修饰符：");
        a.m1();

    }



}
