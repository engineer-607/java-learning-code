package com.nanxinda.cases;

public class case09 {
    public static void main(String[] args) {
        Doctor doctor = new Doctor("jack",32,"doctor",'男',20000);
        Doctor doctor1 = doctor;
        System.out.println(doctor1.equals(doctor1));
    }
}
class Doctor{
    private String name;
    private int age;
    private String job;
    private char gender;
    private double salary;

    public Doctor(String name, int age, String job, char gender, double salary) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.gender = gender;
        this.salary = salary;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }else{
            Doctor doctor = (Doctor) obj;
            if(doctor.age==this.age&&doctor.job.equals(this.job)&&doctor.gender==this.gender&&doctor.name==this.name&&doctor.salary==this.salary){
                return true;
            }
        }
        return false;
    }
}
