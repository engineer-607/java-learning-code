package com.nanxinda.extendsDetail;

public class Base {
    public Base(){
        System.out.println("父类的无参构造器Base()被调用");
    }
    public int n1=100;//public可以访问同类，同包，不同包子类，不同包非子类
    protected int n2=200;//protected可以访问类，同包，不同包子类
    int n3=300;//默认可以访问同类，同包
    private int n4=400;//private可以访问同类
    public int getN4(){
        return n4;
    }
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
    public void getTest04(){
        test04();
    }
}
