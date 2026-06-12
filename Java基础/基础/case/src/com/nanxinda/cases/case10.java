package com.nanxinda.cases;

public class case10 {
    public static void main(String[] args) {
        //向上转型
        Person3 person3 = new Student();
        person3.eat();
        person3.run();
        //向下转型
        Student student = (Student) person3;
        student.study();
        student.run();
        student.eat();
    }
}
class Person3 {
    public void run(){System.out.println("person run"); }
    public void eat() {System.out.println("person eat");}
}
class Student extends Person3 {
    public void run() {
        System.out.println("student run");
    }

    public void study() {
        System.out.println("student study");
    }
}