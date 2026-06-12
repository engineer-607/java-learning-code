package com.nanxinda.cases;

public class case11 {
    public static void main(String[] args) {
        Person4 person4[] = {
                new Teacher3("dong","女",39,13),
                new Teacher3("wang","男",32,9),
                new Student2("lucy","女",20,"2025835"),
                new Student2("jack","男",19,"2025834")
        };
        for (int i = 0; i < person4.length; i++) {
            if(person4[i] instanceof Teacher3){
                System.out.println("老师的信息:\n姓名："+person4[i].getName()+
                        "\n年龄："+person4[i].getAge()+"\n性别："+person4[i].getSex()
                        +"\n工龄；"+((Teacher3)person4[i]).getWork_age()
                );
                person4[i].test();
                ((Teacher3) person4[i]).play();
                System.out.println("---------------");
            } else if (person4[i] instanceof Student2) {
                System.out.println("学生的信息:\n姓名："+person4[i].getName()+
                        "\n年龄："+person4[i].getAge()+"\n性别："+person4[i].getSex()
                        +"\n学号；"+((Student2)person4[i]).getStu_id());
                person4[i].test();
                ((Student2)person4[i]).play();
                System.out.println("---------------");

            }
        }

    }
}
class Person4{
    private String name;
    private String sex;
    private int age;

    public Person4(String name, String sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void test(){
        if(this instanceof Teacher3){
            ((Teacher3)this).teach();
        }else if(this instanceof Student2){
            ((Student2)this).study();
        }
    }
}
class Student2 extends Person4{
    private String stu_id;
    public Student2(String name, String sex, int age, String stu_id) {
        super(name, sex, age);
        this.stu_id = stu_id;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void study(){
        System.out.println("我承诺，我会好好学习");
    }
    public void play(){
        System.out.println(getName()+"爱玩足球");
    }
}
class Teacher3 extends Person4{
    private int work_age;
    public Teacher3(String name, String sex, int age, int work_age) {
        super(name, sex, age);
        this.work_age = work_age;
    }

    public int getWork_age() {
        return work_age;
    }

    public void teach(){
        System.out.println("我承诺，我会认真教学");
    }
    public void play(){
        System.out.println(getName()+"爱玩象棋");
    }
}