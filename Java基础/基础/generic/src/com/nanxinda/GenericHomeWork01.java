package com.nanxinda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

@SuppressWarnings({"all"})
public class GenericHomeWork01 {
    public static void main(String[] args) {
        ArrayList<Employee> arrayList = new ArrayList<>();
        arrayList.add(new Employee("jack",30000,new MayDate(2026,2,11)));
        arrayList.add(new Employee("jack",40000,new MayDate(2025,2,1)));
        arrayList.add(new Employee("tom",20000,new MayDate(2024,3,21)));
        arrayList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                String nameO1 = o1.getName();
                String nameO2 = o2.getName();
                if(nameO1.equals(nameO2)){
                    return MayDate.Compare(o1.getBirthday(),o2.getBirthday());
                }
                return nameO1.compareTo(nameO2);
            }
        });
        Iterator<Employee> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Employee next =  iterator.next();
            System.out.println(next);
        }
    }
}
class Employee{
    private String name;
    private double sal;
    private MayDate birthday;

    public Employee(String name, double sal, MayDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public MayDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MayDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}'+"\n";
    }
}
class MayDate{
    private int month;
    private int day;
    private int year;

    public MayDate(int year,int month, int day) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public static int Compare(MayDate date1,MayDate date2){
//        if(date1.year>date2.year){
//            return 1;
//        } else if (date1.year< date2.year) {
//            return -1;
//        }else {
//            if(date1.month> date2.month){
//                return 1;
//            } else if (date1.month< date2.month) {
//                return -1;
//            }else {
//                if(date1.day> date2.day){
//                    return 1;
//                }else if (date1.day<date2.day){
//                    return -1;
//                }
//            }
//        }
        //优化：
        int n1 = date1.year- date2.year;
        if(n1==0){
            int n2 = date1.month - date2.month;
            if(n2==0){
                int n3 = date1.day- date2.day;
                if(n3==0){
                    return 0;
                }
                return n3;
            }
            return n2;
        }
        return n1;
    }

    @Override
    public String toString() {
        return "MayDate{" +
                "month=" + month +
                ", day=" + day +
                ", year=" + year +
                '}';
    }
}