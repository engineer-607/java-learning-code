package com.nanxinda.test;

public class A {
    //四个属性使用不同修饰符
    public int n1=100;
    protected int n2=200;
    int n3=300;
    private int n4=400;
    public void A(){
        //对于同类四个访问修饰都可以访问
        System.out.println("在同一个类下 n1="+n1+" n2="+n2+" n3="+n3+" n4="+n4);
        A a = new A();
        //
        System.out.println("在同一类下可访问的修饰符：");
        a.m1();
        a.m2();
        a.m3();
        a.m4();
    }
    public void m1(){
        System.out.println("public");
    }
    void m2(){
        System.out.println("默认");
    }
    protected void m3(){
        System.out.println("protected");
    }
    private void m4(){
        System.out.println("private");
    };
}
