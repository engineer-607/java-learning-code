package com.nanxinda.Object.Debug;

public class DeBug03 {
    public static void main(String[] args) {
        //使用debug了解对象创建的流程
        Person jack = new Person("jack", 18);
        System.out.println(jack);


    }
}
class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}