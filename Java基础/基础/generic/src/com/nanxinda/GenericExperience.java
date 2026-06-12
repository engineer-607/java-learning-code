package com.nanxinda;

import java.util.ArrayList;
@SuppressWarnings({"all"})
public class GenericExperience {
    public static void main(String[] args) {
        ArrayList<Dog> arrayList = new ArrayList<Dog>();
        //1.ArrayList<Dog>表示ArrayList集合中的元素是Dog类型
        arrayList.add(new Dog("大黄",9));
        //2.如果编译器发现添加的类型不满足需求，就会报错
        arrayList.add(new Dog("小白",8));
        for (Dog dog :arrayList) {
            //3.遍历的时候，可以直接取出Dog类型而不是Object
            System.out.println(dog);
        }
    }
    //优点：1）检查添加元素的类型，提高安全性
    //     2）减少
}
class Dog{
    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}