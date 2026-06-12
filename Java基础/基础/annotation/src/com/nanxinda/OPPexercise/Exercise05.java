package com.nanxinda.OPPexercise;

public class Exercise05 {
    public static void main(String[] args) {
        A a = new A();
        a.m1();
    }
}
class A{
    private final String NAME = "张三";
    public void m1(){
        class B{
            private final static String NAME ="jack";
            public void show(){
                System.out.println(NAME);
                System.out.println(A.this.NAME);
            }
        }
        B b = new B();
        b.show();
    }
}