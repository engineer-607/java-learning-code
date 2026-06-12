package com.nanxinda.extendsDetail;
//快捷键：ctrl+H可以查到继承关系
public class Sub extends Base{
    /*
    1)子类构造器必须调用父类的构造器，完成父类的初始化
    2)当创建子类对象时，不管使用子类的哪个构造器，默认情况下总会去调用父类的无参构造器
    3)super()必须写在子类构造器的第一步
    4)super和this都只能放在构造器的第一行，所以二者不能在同一个构造器的同时被调用
    5)Java所有类都是Object类的子类，Object是所有类的基类
    6)父类的构造器的调用不限于直接父亲，将一直往上追溯到Object类
    7)子类最多只能继承一个父类（直接继承），java中的单继承机制
    8)不能滥用继承，子类和父类必须满足is-a的逻辑关系
     */
    public Sub(){
        super();//会子类的无参构造器自动调用父类的默认构造器

        System.out.println("子类Sub的无参构造器会被调用");
    }
    public Sub(String name){
        super();
        System.out.println("子类Sub(String name)的有参构造器会被调用");
    }
     public void getBase(){
        System.out.println("n1="+n1+" n2="+n2+" n3="+n3);
        System.out.println("通过公共方法获得n4，n4="+getN4());
        test01();
        test02();
        test03();
        System.out.println("通过公共方法获得test04方法");
        getTest04();
    }

}
