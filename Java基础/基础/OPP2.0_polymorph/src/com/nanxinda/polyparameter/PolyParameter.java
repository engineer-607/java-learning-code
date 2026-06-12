package com.nanxinda.polyparameter;

public class PolyParameter {
    public static void main(String[] args) {
        Test test = new Test();
        Worker jack = new Worker("jack", 12000);
        Manger mike = new Manger("mike", 20000, 10000);
        System.out.println(test.showEmpAnnual(jack));
        System.out.println(test.showEmpAnnual(mike));
        test.testWork(jack);
        test.testWork(mike);

    }
}
class Test{
    public double showEmpAnnual(Employee employee){
        return employee.getAnnual();
    }
    public void testWork(Employee employee){
        if(employee instanceof Manger){
            ((Manger)employee).manage();
        }else if(employee instanceof Worker){
            ((Worker)employee).work();
        }else{
            System.out.println("输入的类型错误");
        }
    }
}
class Employee{
    private String name;
    private double monthSalary;

    public Employee(String name, double monthSalary) {
        this.name = name;
        this.monthSalary = monthSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMonthSalary() {
        return monthSalary;
    }

    public void setMonthSalary(double monthSalary) {
        this.monthSalary = monthSalary;
    }
    public double getAnnual(){
        return 12*monthSalary;
    }
}
class Manger extends Employee{
    private double bonus;

    public Manger(String name, double monthSalary, double bonus) {
        super(name, monthSalary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public double getAnnual() {
        return super.getAnnual()+bonus;
    }
    public void manage(){
        System.out.println(super.getName()+"正在管理");
    }
}
class Worker extends Employee{
    public Worker(String name, double monthSalary) {
        super(name, monthSalary);
    }

    @Override
    public double getAnnual() {
        return super.getAnnual();
    }
    public void work(){
        System.out.println(super.getName()+"正在工作");
    }
}