package com.nanxinda;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings({"all"})
public class GenericDetail {
    public static void main(String[] args) {
        //泛（广泛）型（类型）=>Integer、String、Dog...
        //1）泛型又称为参数化类型，解决数据类型的安全性问题
        //2）在类声明或实例化时指定好需要的具体类型
        //ArrayList<E> ArrayList<Dog>  相当于把Dog赋值给E
        //3）可以保证程序在编译时没有发出警告，运行时不会产生ClassCastException
        //4）泛型的作用：可以在类声明时通过一个标识表示类中某个属性的类型，或者某个官方的
        //返回值类型，或者是参数类型
        Person<String> stringPerson = new Person<String>("jack");
        /*
class Person{
    String s;
    //即在编译期间，就确定E的类型
    public Person(String s){
        this.s = s;
    }
    public String f(){
        return s;
    }
    public void show(){
        System.out.println(s.getClass());
    }
}
         */
        stringPerson.show();
    }

///==========================================
    //泛型的声明：
    //1）interface<E>{}和class类<K,V>{}
    //比如：List、ArrayList
    //2）其中，T、K、V不代表值，而是表示类型
    //任意字母都可以，常用T表示，是Type的缩写
///===========================================
    //1.给泛型指向数据类型是引用数据类型，不能是基本数据类型
    List<Integer> list = new ArrayList<Integer>();//OK
    //2.在给泛型指定具体类型时，可以传入该类型或者其类型
    Person<A> person = new Person<A>(new A());
    //E->A,所以构造器传入A类对象
    Person<A> person1 = new Person<A>(new B());
    //也可以传入其子类
    //3.泛型使用形式（简写）/推荐使用
    ArrayList<Integer> arrayList = new ArrayList<>();//编译器会进行类型推断
    //4.如果不声明泛型，默认是Object
    ArrayList arrayList2 = new ArrayList();
    //ArrayList<Object> arrayList2 = new ArrayList<Object>();
    Person person2 = new Person();
    /*
class Person{
    Object s;

    public Person() {
    }

    public Person(Object s){
        this.s = s;
    }
    public Object f(){
        return s;
    }
    public void show(){
        System.out.println(s.getClass());
    }
}
     */




}
class Person<E>{
    E s;//E 表示 s的数据类型，该数据类型在定义Person对象的时候指定
    //即在编译期间，就确定E的类型

    public Person() {
    }

    public Person(E s){
        this.s = s;
    }
    public E f(){
        return s;
    }
    public void show(){
        System.out.println(s.getClass());
    }
}
class A{}
class B extends A{}