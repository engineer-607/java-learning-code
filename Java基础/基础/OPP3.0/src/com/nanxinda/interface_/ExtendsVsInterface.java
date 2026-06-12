package com.nanxinda.interface_;

public class ExtendsVsInterface {
    public static void main(String[] args) {
        /*
        当子类继承父类，就自动地拥有父类的功能
        如果子类需要扩展功能，可以通过实现接口的方式扩展
        可以理解实现接口是对java单继承机制的一种补充
         */
        LittleMonkey littleMonkey = new LittleMonkey("悟空");
        littleMonkey.swimming();
    }
}
/*
实现接口vs继承类
继承的价值主要在于：解决代码的复用性和可维护性
接口的价值主要在于：设计，设计好各种规范（方法），让其他类去实现这些方法
即更加灵活
 */
/*
接口比继承更加灵活，继承满足is-a的关系，而接口只需满足like-a的关系
 */
class Monkey{
    private String name;

    public Monkey(String name) {
        this.name = name;
    }
    public void climbing(){
        System.out.println("猴子会爬树...");
    }

    public String getName() {
        return name;
    }
}
//继承
class LittleMonkey extends  Monkey implements Fishable{

    public LittleMonkey(String name) {
        super(name);
    }

    @Override
    public void swimming() {
        System.out.println(super.getName()+"会像鱼一样游泳");
    }
}
interface Fishable{
    void swimming();
}