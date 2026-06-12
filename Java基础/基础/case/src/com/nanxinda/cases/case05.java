package com.nanxinda.cases;

public class case05 {
    public static void main(String[] args) {
        System.out.println(new Farmer(3000,13).toString());
        System.out.println(new Worker(3000,13).toString());
        System.out.println(new Waiter(4000,14).toString());
        System.out.println(new Teacher2(5000,16,30,290).toString());
        System.out.println(new Scientist(6000,12,10000).toString());
    }
}
class Employee2{
    private double salary;
    private double monthlySalary;
    private double month;
    //属性考虑带薪月数


    public Employee2(double monthlySalary, double month) {
        this.monthlySalary = monthlySalary;
        this.month = month;
        this.salary=monthlySalary*month;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public void setMonth(double month) {
        this.month = month;
    }

    public double getSalary() {
        return salary;
    }
    public String print(){
        return "salary="+salary;
    }

}
class Worker extends Employee2{
    public Worker( double monthlySalary, double month) {
        super(monthlySalary, month);
    }

    @Override
    public String toString() {
        return "Worker-"+super.print();
    }
}
class Farmer extends Employee2{
    public Farmer( double monthlySalary, double month) {
        super( monthlySalary, month);
    }

    @Override
    public String toString() {
        return "Farmer-"+super.print();
    }
}
class Waiter extends Employee2{
    public Waiter( double monthlySalary, double month) {
        super(monthlySalary, month);
    }

    @Override
    public String toString() {
        return "Waiter-"+super.print();
    }
}
class Teacher2 extends Employee2{

    private double courseSalary;
    private int day ;
    private double salary = super.getSalary()+courseSalary*day;

    public Teacher2(double monthlySalary, double month, double courseSalary, int day) {
        super(monthlySalary, month);
        this.courseSalary = courseSalary;
        this.day = day;
    }

    @Override
    public String toString() {
        return "Teacher-" +
                "salary=" +salary;
    }
}
class Scientist extends Employee2{
    private double bonus = 10000;
    private double salary = bonus+super.getSalary();

    public Scientist( double monthlySalary, double month, double bonus) {
        super( monthlySalary, month);
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return "Scientist-" +
                "salary=" + salary ;
    }
}
