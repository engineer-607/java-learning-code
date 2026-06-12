package com.nanxinda.innerclass_;

public class AnonymousInnerDetail {
    /*
    匿名内部类的语法比较奇特，因为匿名内部类既是一个类的定义
    同时它本身也是一个对象（语法上，既有类的特征，也有创建对象的特征）
     */
    public static void main(String[] args) {
        Outer05 outer05 = new Outer05();
        outer05.f1();

    }
}
class Outer05{
    private int age = 99;
    public void f1(){
        //创建一个基于类的匿名内部类
        Person person = new Person("jack"){
            private int age = 98;
            @Override
            public void say() {
                System.out.println(getName()+"在说话");
                //1.可以直接访问外部类的所有成员，包括私有
                System.out.println(getName()+"的年龄为"+age);//就近原则，98
                //如果像访问外部类的重名的成员变量，可以使用Outer05.this.XXX
                System.out.println(Outer05.this.age);//99
            }
        };
        //3.作用域：仅仅在定义它的方法或者代码块中
        person.say();//动态绑定机制，会找到运行类型Outer05$1
        //直接调用
        //2.不能添加访问修饰符，因为其地位本质还是一个局部变量
        new Person("jack"){
            @Override
            public void say() {
                System.out.println(getName()+"在说话");
            }
        }.say();
    }
}
class Person{
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void say(){
        System.out.println("人在说话");
    }
}
