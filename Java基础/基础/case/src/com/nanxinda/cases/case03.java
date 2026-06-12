package com.nanxinda.cases;

public class case03 {
    public static void main(String[] args) {
        Professor professor = new Professor("wang" ,52,"CS-professor");
        System.out.println(professor.introduce());
    }
}
class Teacher{
    private String name;
    private int age;
    private String post;
    private double salary=10000;

    public Teacher(String name, int age, String post) {
        this.name = name;
        this.age = age;
        this.post = post;
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

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public double getSalary() {
        return salary;
    }



    public String introduce() {
        return  "name='" + name + '\'' +
                ", age=" + age +
                ", post='" + post + '\'' +
                ", salary=" + salary;
    }
}
class Professor extends Teacher{
    private double salary = super.getSalary()*1.3;
    public Professor(String name, int age, String post) {
        super(name, age, post);
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String introduce() {
        return "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", post='" + super.getPost() + '\'' +
                ", salary=" + salary;
    }
}
class AssociateProfessor extends Teacher{
    private double salary = super.getSalary()*1.2;
    public AssociateProfessor(String name, int age, String post) {
        super(name, age, post);
    }

    @Override
    public double getSalary() {
        return salary;
    }
}
class Lecturer extends  Teacher{
    private double salary = super.getSalary()*1.1;
    public Lecturer(String name, int age, String post) {
        super(name, age, post);
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String introduce() {
        return "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", post='" + super.getPost() + '\'' +
                ", salary=" + salary;
    }
}