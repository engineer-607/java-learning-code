package com.nanxinda.codeblock;

public class CodeBlockDetail01 {
    /*
    代码块使用细节1.0：
    1）static代码块也叫做静态代码块，作用就是对类进行初始化，而且它随着类的加载而执行
    并且只会执行一次
    2）类加载的时机：（子类加载时会先加载父类）
    1.创建对象实例时（new）
    2.创建子类对象实例，父类也会被加载
    3.使用类的静态成员时（静态属性，静态方法）
    3）普通的代码块，在创建对象实例时，会被隐式调用，被创建一次，就会调用一次
    如果只是使用类的静态成员时，普通代码块并不会执行（可以理解为普通代码块为
    构造器的补充，只用构造器被调用时普通代码块才会执行）
     */
    public static void main(String[] args) {
        //对象实例
        BB bb = new BB();
        //子类实例时
        AA aa = new AA();
        //静态成员使用时
        System.out.println(Cat.n1);
    }
}
class AA extends BB{
    //静态代码块
    static {
        System.out.println("AA的静态代码块被执行");
    }
}
class BB{
    static {
        System.out.println("BB的静态代码块被执行");
    }
}
class Cat{
    public static  int n1=100;
    static {
        System.out.println("Cat的静态代码块被执行");
    }
}
