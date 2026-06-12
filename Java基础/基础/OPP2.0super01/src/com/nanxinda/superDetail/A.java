package com.nanxinda.superDetail;

public class A {
    public int n1=100;
    protected int n2=200;
    int n3=300;
    private int n4=400;
    public void test01(){
        System.out.println("test01");
    }
    protected void test02(){
        System.out.println("test02");
    }
    void test03(){
        System.out.println("test03");
    }

    private void test04(){
        System.out.println("test04");
    }
    public void cal(){
        System.out.println("A类的cal()方法");
    }
}
