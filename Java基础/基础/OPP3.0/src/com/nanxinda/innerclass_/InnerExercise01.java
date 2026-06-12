package com.nanxinda.innerclass_;

/**
 * 匿名内部类的最佳实践
 * 当作实参直接传递，简介高效
 */
public class InnerExercise01 {
    public static void main(String[] args) {
        f1(new IA() {
            @Override
            public void show() {
                System.out.println("show");
            }
        });
        CellPhone cellPhone = new CellPhone();
        cellPhone.alarmClock(new Bell() {
            @Override
            public void ring() {
                System.out.println("懒猪起床了");
            }
        });
        cellPhone.alarmClock(new Bell() {
            @Override
            public void ring() {
                System.out.println("小伙伴上课了");
            }
        });
    }
    public static void f1(IA ia){
        ia.show();
    }
}
interface IA{
    public void show();
}
//new XXX(){}本质上创建一个继承普通类或抽象类或者实现接口的匿名类，然后调用其默认构造器进行实例
//作业
interface Bell{
    void ring();
}
class CellPhone{
    public void alarmClock(Bell bell){
        bell.ring();
    }
}