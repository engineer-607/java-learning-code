package com.nanxinda.class_.homework_;

public class HomeWork05 {
    public static void main(String[] args) {
        String s1 = "hspedu";
        Animal a = new Animal(s1);
        Animal b = new Animal(s1);
        System.out.println(a == b);          // false
        System.out.println(a.equals(b));     // false
        //错误分析：注意Animal没有重写equals方法
        System.out.println(a.name == b.name);// true
        String s4 = new String("hspedu");
        String s5 = "hspedu";
        System.out.println(s1 == s4);  // false
        System.out.println(s4 == s5);  // false
        String t1 = "hello" + s1;
        //结果分析：常量池中存储"hello","hspedu"
        //普通堆中存储String对象，其value指向char数组{'h','e'.....}
        String t2 = "hellohspedu";
        System.out.println(t1.intern() == t2);  // true
        //使用intern方法，如果没有该字面量对象，则在常量池中存储"hellohspedu"对象

    }
}
class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }
}