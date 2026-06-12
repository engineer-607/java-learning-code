package com.nanxinda.cases;

public class case04 {
    public static void main(String[] args) {
        Manager jack = new Manager("jack", 600, 250);
        jack.print();
        Staff mike = new Staff("mike", 300, 290);
        mike.print();


    }
}
class Employee{
    private String name;
    private double daySalary;
    private int day;
    private double salary = daySalary*day;

    public Employee(String name, double daySalary, int day) {
        this.name = name;
        this.daySalary = daySalary;
        this.day = day;
    }
    public void print(){
        System.out.println("name="+name+" salary="+salary);
    }

    public String getName() {
        return name;
    }

    public double getDaySalary() {
        return daySalary;
    }

    public int getDay() {
        return day;
    }
}
class Manager extends Employee{
    private double salary =super.getDaySalary()*super.getDay()*1.2+1000;
    public Manager(String name, double daySalary, int day) {
        super(name, daySalary, day);
    }
    public void print(){
        System.out.println("name="+super.getName()+" salary="+salary);
    }
}
class Staff extends Employee{
    private double salary = super.getDaySalary()*super.getDay()*1.0;
    public Staff(String name, double daySalary, int day) {
        super(name, daySalary, day);
    }
    public void print(){
        System.out.println("name="+super.getName()+" salary="+salary);
    }
}