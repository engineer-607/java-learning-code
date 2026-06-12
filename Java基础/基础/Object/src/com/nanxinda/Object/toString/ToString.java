package com.nanxinda.Object.toString;

public class ToString {
    /*
    toString方法：
    默认返回：全类名+@+哈希值的十六进制
     */
    public static void main(String[] args) {
        /*
        Object中toString方法源码
        1）getClass().getName() 类的全类名（包名+类名）、
        2）Integer.toHexString(hashCode() 将对象的hashCode值转成16进制
            public String toString() {
        return getClass().getName() + "@" + Integer.toHexString(hashCode());
    }
         */
        Monster monster = new Monster("小妖怪","巡山",1000);
        System.out.println(monster.toString());

    }
}
class Monster{
    private String name;
    private String job;
    private double salary;
    public Monster(String name, String job, double salary) {
        this.name = name;
        this.job = job;
        this.salary = salary;
    }
    //重写toString方法，输出对象的属性
    //使用快捷键alt+insert -> toString

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                '}';
    }
}
