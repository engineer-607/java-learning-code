package com.nanxinda.set_;

import java.util.HashSet;
import java.util.Objects;

@SuppressWarnings({"all"})
public class HashSetExercise {
    public static void main(String[] args) {
        HashSet set = new HashSet();
        set.add(new Employee("jack",18));
        set.add(new Employee("john",19));
        set.add(new Employee("jack",18));
        System.out.println("set="+set);
    }
}
class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        Employee employee;
        if(obj instanceof Employee) {
             employee = (Employee) obj;
            return employee.age==this.age&&employee.name.equals(this.name);
        }
        return false;
    }

    //如果name和age相同，则返回相同的hash值
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
