package com.nanxinda.abstract_;

public class Abstract {
    /*
    当父类某些方法需要声明但不知道如何实现，可以将该方法声明为抽象方法
    这个类就是抽象类
     */
    public static void main(String[] args) {
        /*
        抽象类
        语法：访问修饰符 abstract 返回类型 类名{}
             访问修饰符 abstract 返回类型 方法名(参数列表){}
        抽象类的价值更多作用于设计，设计好后，让子类继承并实现抽象类
        细节：
        1）抽象类不能被实例化
        2）抽象类不一定要包含abstract方法
        3）abstract只能修饰类和方法，不能修饰属性和其他的
        4）抽象类可以有任意成员，【抽象类本质还是类，比如非抽象方法，构造器（虽然抽象类不能实例，但是子类
        可能会用到父类的构造器），静态属性等等
        5）如果一个类继承了抽象类，则必须实现抽象类的所有抽象方法，
        除非自己也声明为abstract类
        6）抽象类不能使用private,final和static类修饰，因为这些关键字和重写违背

         */


    }
}
abstract class Animal{
    private String name;

    public Animal(String name) {
        this.name = name;
    }
    //父类方法不确定性问题：
    //===》考虑将方法设计为抽象(abstract)方法
    //===》抽象方法是没有方法体的方法
    //===》当一个类中存在抽象方法时，需要将该类抽象为抽象类
    public abstract void eat();
}
abstract class A{}
class Dog extends Animal{
    public Dog(String name) {
        super(name);
    }

    @Override
    public void eat() {

    }
}
abstract class Mammal extends Animal{
    public Mammal(String name) {
        super(name);
    }
}