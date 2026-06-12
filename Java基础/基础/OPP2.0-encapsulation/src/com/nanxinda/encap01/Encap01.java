package com.nanxinda.encap01;

public class Encap01 {
    public String name;
    private int age;
    private double salary;
    public String job;
    public Encap01(){}
    public Encap01(String name,int age,double salary,String job){
        if(name.length()>=2&&name.length()<=6){
            this.name=name;
        }else{
            System.out.println("姓名长度有误，控制在2-6个字符之间，给出默认名称无名");
            this.name="无名";
        }
        if(age>=1&&age<=120){
            this.age=age;
        }else{
            System.out.println("年龄需要控制在1-120之间，给出默认年龄18");
            this.age=18;
        }
        this.salary=salary;
        this.job=job;
        //可以使用set方法，无需重新再写
    }
    public Encap01(String name,double salary,String job,int age){
        this.setSalary(salary);
        this.setName(name);
        this.setJob(job);
        this.setAge(age);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length()>=2&&name.length()<=6){
        this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age>=1&&age<=120){
        this.age = age;
        }else{
            System.out.println("输入的年龄错误，给出默认年龄18");
            this.age=18;
        }
    }

    private double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    public void info(){
        System.out.println("name="+name+" age="+age+" salary="+salary);
    }
}
