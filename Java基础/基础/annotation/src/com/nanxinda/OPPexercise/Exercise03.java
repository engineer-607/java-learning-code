package com.nanxinda.OPPexercise;

public class Exercise03 {
    public static void main(String[] args) {
        Animal cat = new Cat();
        Animal dog = new Dog();
        cat.shout();
        dog.shout();
    }
}
abstract class Animal{
    abstract public void shout();
}
class Dog extends Animal{

    @Override
    public void shout() {
        System.out.println("狗会汪汪叫");
    }
}
class Cat extends Animal{

    @Override
    public void shout() {
        System.out.println("猫会喵喵叫");
    }
}